package customer.tms_java.domain;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sap.cds.ql.Delete;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.transportationdemanddomainservice.TransportationDemandDomainService_;
import cds.gen.tms.FreightOrder;
import cds.gen.tms.FreightOrder_;
import cds.gen.tms.FreightOrderItem;
import cds.gen.tms.FreightOrderItem_;
import cds.gen.tms.FreightOrderStop;
import cds.gen.tms.FreightOrderStop_;
import cds.gen.tms.TransportationDemand_;
import cds.gen.tms.TransportationDemandItem;
import cds.gen.tms.TransportationDemandItem_;
import cds.gen.transportationdemanddomainservice.TransportationDemands;
import cds.gen.transportationdemanddomainservice.TransportationDemands_;

@Component
@ServiceName(TransportationDemandDomainService_.CDS_NAME)
public class TransportationDemandDomainService {
  private static final String IN_PLANNING = "IN_PLANNING";

  private final PersistenceService db;

  public TransportationDemandDomainService(PersistenceService db) {
    this.db = db;
  }

  public TransportationDemands assign(String tdId, String freightOrderId) {
    TransportationDemands td = getById(tdId);

    if (td.getFreightOrderId() != null) {
      throw new ServiceException(ErrorStatuses.CONFLICT,
          "Transportation Demand is already assigned to a Freight Order");
    }

    FreightOrder fo = db.run(Select.from(FreightOrder_.class).where(f -> f.ID().eq(freightOrderId)))
        .single(FreightOrder.class);

    if (!IN_PLANNING.equals(fo.getStatusCode())) {
      throw new ServiceException(ErrorStatuses.CONFLICT, "Cannot assign TD: Freight Order must be IN_PLANNING");
    }

    List<FreightOrderStop> stops = db.run(Select.from(FreightOrderStop_.class)
        .where(s -> s.freightOrder_ID().eq(freightOrderId)).orderBy(s -> s.sequence().asc()))
        .listOf(FreightOrderStop.class);

    if (stops.isEmpty()) {
      addStop(freightOrderId, td.getFromLocationId(), 1);
      addStop(freightOrderId, td.getToLocationId(), 2);
    } else {
      FreightOrderStop firstStop = stops.get(0);
      if (!firstStop.getLocationId().equals(td.getFromLocationId())) {
        throw new ServiceException(ErrorStatuses.CONFLICT,
            "TD's start location must match the origin of the Freight Order");
      }
      boolean toLocationExists = stops.stream().anyMatch(s -> s.getLocationId().equals(td.getToLocationId()));
      if (!toLocationExists) {
        addStop(freightOrderId, td.getToLocationId(), stops.size() + 1);
      }
    }

    db.run(Update.entity(TransportationDemands_.class).data(TransportationDemands.FREIGHT_ORDER_ID, freightOrderId)
        .where(t -> t.ID().eq(tdId)));

    List<TransportationDemandItem> tdItems = db
        .run(Select.from(TransportationDemandItem_.class).where(i -> i.transportationDemand_ID().eq(tdId)))
        .listOf(TransportationDemandItem.class);

    for (TransportationDemandItem tdItem : tdItems) {
      FreightOrderItem item = FreightOrderItem.create();

      item.setId(UUID.randomUUID().toString());
      item.setDisplayId(generateDisplayId("FOI"));
      item.setFreightOrderId(freightOrderId);
      item.setProductName(tdItem.getProductName());
      item.setQuantity(tdItem.getQuantity());
      item.setTransportationDemandItemId(tdItem.getId());

      db.run(Insert.into(FreightOrderItem_.class).entry(item));
    }

    return getById(tdId);
  }

  public TransportationDemands unassign(String tdId) {
    TransportationDemands td = getById(tdId);

    if (td.getFreightOrderId() == null) {
      throw new ServiceException(ErrorStatuses.BAD_REQUEST,
          "Transportation Demand is not assigned to any Freight Order");
    }

    String foId = td.getFreightOrderId();
    String toLocationId = td.getToLocationId();

    List<TransportationDemandItem> items = db
        .run(Select.from(TransportationDemandItem_.class).where(i -> i.transportationDemand_ID().eq(tdId)))
        .listOf(TransportationDemandItem.class);

    items.forEach(item -> db
        .run(Delete.from(FreightOrderItem_.class).where(i -> i.transportationDemandItem_ID().eq(item.getId()))));

    db.run(Update.entity(TransportationDemands_.class).data(TransportationDemands.FREIGHT_ORDER_ID, null)
        .where(t -> t.ID().eq(tdId)));

    boolean noOtherTdsWithSameToLocation = db.run(Select.from(TransportationDemand_.class)
        .where(t -> t.freightOrder_ID().eq(foId).and(t.toLocation_ID().eq(toLocationId)))).first().isEmpty();

    if (noOtherTdsWithSameToLocation) {
      db.run(Delete.from(FreightOrderStop_.class)
          .where(s -> s.freightOrder_ID().eq(foId).and(s.location_ID().eq(toLocationId))));
      reorderStops(foId);
    }

    boolean noRemainingTds = db.run(Select.from(TransportationDemand_.class).where(t -> t.freightOrder_ID().eq(foId)))
        .first().isEmpty();

    if (noRemainingTds) {
      db.run(Delete.from(FreightOrderStop_.class).where(s -> s.freightOrder_ID().eq(foId)));
    }

    return getById(tdId);
  }

  private void addStop(String foId, String locationId, int sequence) {
    FreightOrderStop stop = FreightOrderStop.create();

    stop.setId(UUID.randomUUID().toString());
    stop.setFreightOrderId(foId);
    stop.setLocationId(locationId);
    stop.setSequence(sequence);

    db.run(Insert.into(FreightOrderStop_.class).entry(stop));
  }

  private void reorderStops(String foId) {
    List<FreightOrderStop> stops = db.run(
        Select.from(FreightOrderStop_.class).where(s -> s.freightOrder_ID().eq(foId)).orderBy(s -> s.sequence().asc()))
        .listOf(FreightOrderStop.class);

    for (int i = 0; i < stops.size(); i++) {
      FreightOrderStop stop = stops.get(i);
      int newSeq = i + 1;
      if (stop.getSequence() != newSeq) {
        db.run(Update.entity(FreightOrderStop_.class).data(FreightOrderStop.SEQUENCE, newSeq)
            .where(s -> s.ID().eq(stop.getId())));
      }
    }
  }

  public boolean isAssigned(String tdId) {
    TransportationDemands td = getById(tdId);
    return td.getFreightOrderId() != null;
  }

  public void validateCanModify(String tdId) {
    if (isAssigned(tdId)) {
      throw new ServiceException(ErrorStatuses.CONFLICT,
          "Transportation Demand is assigned to a Freight Order. Unassign it first.");
    }
  }

  private TransportationDemands getById(String id) {
    return db.run(Select.from(TransportationDemands_.class).where(t -> t.ID().eq(id)))
        .single(TransportationDemands.class);
  }

  private String generateDisplayId(String prefix) {
    return prefix + "-" + UUID.randomUUID().toString().replace("-", "").substring(0, 5).toUpperCase();
  }
}

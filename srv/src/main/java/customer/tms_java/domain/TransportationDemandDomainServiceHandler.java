package customer.tms_java.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sap.cds.ql.Delete;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.tms.FreightOrder;
import cds.gen.tms.FreightOrderItem;
import cds.gen.tms.FreightOrderItem_;
import cds.gen.tms.FreightOrderStop;
import cds.gen.tms.FreightOrderStop_;
import cds.gen.tms.FreightOrder_;
import cds.gen.tms.TransportationDemand;
import cds.gen.tms.TransportationDemandItem;
import cds.gen.tms.TransportationDemandItem_;
import cds.gen.tms.TransportationDemand_;
import cds.gen.transportationdemanddomainservice.TransportationDemandDomainService_;
import cds.gen.transportationdemanddomainservice.TransportationDemands;
import cds.gen.transportationdemanddomainservice.TransportationDemandsAssignContext;
import cds.gen.transportationdemanddomainservice.TransportationDemandsUnassignContext;
import cds.gen.transportationdemanddomainservice.TransportationDemands_;

@Component
@ServiceName(TransportationDemandDomainService_.CDS_NAME)
public class TransportationDemandDomainServiceHandler implements EventHandler {
  private static final String IN_PLANNING = "IN_PLANNING";

  private final PersistenceService db;
  private final CqnAnalyzer cqnAnalyzer;

  public TransportationDemandDomainServiceHandler(PersistenceService db, CdsModel cdsModel) {
    this.db = db;
    this.cqnAnalyzer = CqnAnalyzer.create(cdsModel);
  }

  @On(event = TransportationDemandsAssignContext.CDS_NAME)
  public void onAssign(TransportationDemandsAssignContext ctx) {
    var tdId = getTDIdByCqnSelect(ctx.getCqn());

    var td = getTDByIdOrThrow(tdId);
    validateTDIsNotAssigned(td);

    var freightOrderId = ctx.getFreightOrderId();
    var freightOrder = getFoByIdOrThrow(freightOrderId);
    validateFOStatus(freightOrder, "assign TD");

    var freightOrderStops = getFreightOrderStopsByFOId(freightOrderId);
    if (freightOrderStops.isEmpty()) {
      createFreightOrderStop(freightOrderId, td.getFromLocationId(), 1);
      createFreightOrderStop(freightOrderId, td.getToLocationId(), 2);
    } else {
      validateFirstFoStop(freightOrderStops.get(0), td.getFromLocationId());
      if (!freightOrderStops.stream().anyMatch(s -> s.getLocationId().equals(td.getToLocationId()))) {
        createFreightOrderStop(freightOrderId, td.getToLocationId(), freightOrderStops.size() + 1);
      }
    }

    assignTDToFreightOrder(tdId, freightOrderId);

    var tdItems = getTDItemsByTdId(tdId);
    for (var tdItem : tdItems) {
      createFreightOrderItem(mapTDItemToFOItem(tdItem, freightOrderId));
    }

    ctx.setCompleted();
  }

  @On(event = TransportationDemandsUnassignContext.CDS_NAME)
  public void onUnassign(TransportationDemandsUnassignContext ctx) {
    var tdId = getTDIdByCqnSelect(ctx.getCqn());

    var td = getTDByIdOrThrow(tdId);
    validateTDIsAssigned(td);

    var foId = td.getFreightOrderId();
    var toLocationId = td.getToLocationId();

    var tdItems = getTDItemsByTdId(tdId);
    for (var tdItem : tdItems) {
      deleteFreightOrderItemByTdItemId(tdItem.getId());
    }

    unassignTD(tdId);

    if (getTDByFoIdAndToLocationId(foId, toLocationId).isEmpty()) {
      deleteFreightOrderStop(foId, toLocationId);
      reorderFOStops(foId);
    }

    if (getTDByFoId(foId).isEmpty()) {
      deleteFreightOrderStopByFoId(foId);
    }

    ctx.setCompleted();
  }

  private void reorderFOStops(String foId) {
    var foStops = getOrderedFreightOrderStopsByFOId(foId);
    for (int i = 0; i < foStops.size(); i++) {
      var foStop = foStops.get(i);
      if (foStop.getSequence() != i + 1) {
        updateFOStopSequence(foStop.getId(), i + 1);
      }
    }
  }

  private void createFreightOrderStop(String foId, String locationId, int sequence) {
    var freightOrderStop = FreightOrderStop.create();

    freightOrderStop.setId(UUID.randomUUID().toString());
    freightOrderStop.setLocationId(locationId);
    freightOrderStop.setFreightOrderId(foId);
    freightOrderStop.setSequence(sequence);

    db.run(Insert.into(FreightOrderStop_.class).entry(freightOrderStop));
  }

  private FreightOrderItem mapTDItemToFOItem(TransportationDemandItem tdItem, String foId) {
    var foItem = FreightOrderItem.create();

    foItem.setFreightOrderId(foId);
    foItem.setQuantity(tdItem.getQuantity());
    foItem.setId(UUID.randomUUID().toString());
    foItem.setProductName(tdItem.getProductName());
    foItem.setTransportationDemandItemId(tdItem.getId());
    foItem.setDisplayId(generateDisplayId("FOI"));

    return foItem;
  }

  private void validateFirstFoStop(FreightOrderStop freightOrderStop, String tdFromLocationId) {
    if (!freightOrderStop.getLocationId().equals(tdFromLocationId)) {
      throw new ServiceException(ErrorStatuses.CONFLICT,
          "TD's start location must match the origin of the Freight Order");
    }
  }

  private void validateTDIsAssigned(TransportationDemand td) {
    if (td.getFreightOrderId() == null) {
      throw new ServiceException(ErrorStatuses.BAD_REQUEST,
          "Transportation Demand is not assigned to any Freight Order");
    }
  }

  private void validateTDIsNotAssigned(TransportationDemand td) {
    if (td.getFreightOrderId() != null) {
      throw new ServiceException(ErrorStatuses.CONFLICT, "Transportation Demand already assigned");
    }
  }

  private void validateFOStatus(FreightOrder freightOrder, String action) {
    if (!IN_PLANNING.equals(freightOrder.getStatusCode())) {
      throw new ServiceException(ErrorStatuses.CONFLICT, "Cannot " + action + ": Freight Order must be IN_PLANNING");
    }
  }

  private String getTDIdByCqnSelect(CqnSelect cqn) {
    return (String) cqnAnalyzer.analyze(cqn).targetKeys().get(TransportationDemands_.ID);
  }

  private void createFreightOrderItem(FreightOrderItem item) {
    db.run(Insert.into(FreightOrderItem_.class).entry(item));
  }

  private void deleteFreightOrderItemByTdItemId(String tdItemId) {
    db.run(Delete.from(FreightOrderItem_.class).where(i -> i.transportationDemandItem_ID().eq(tdItemId)));
  }

  private void deleteFreightOrderStop(String foId, String locationId) {
    db.run(Delete.from(FreightOrderStop_.class)
        .where(s -> s.freightOrder_ID().eq(foId).and(s.location_ID().eq(locationId))));
  }

  private void deleteFreightOrderStopByFoId(String foId) {
    db.run(Delete.from(FreightOrderStop_.class).where(s -> s.freightOrder_ID().eq(foId)));
  }

  private FreightOrderStop updateFOStopSequence(String foStopId, int sequence) {
    return db.run(Update.entity(FreightOrderStop_.class).data(FreightOrderStop.SEQUENCE, sequence)
        .where(s -> s.ID().eq(foStopId))).first(FreightOrderStop.class).orElseThrow();
  }

  private List<TransportationDemandItem> getTDItemsByTdId(String tdId) {
    return db.run(Select.from(TransportationDemandItem_.class).where(i -> i.transportationDemand_ID().eq(tdId)))
        .listOf(TransportationDemandItem.class);
  }

  private void assignTDToFreightOrder(String tdId, String freightOrderId) {
    db.run(Update.entity(TransportationDemands_.class).data(TransportationDemands.FREIGHT_ORDER_ID, freightOrderId)
        .where(t -> t.ID().eq(tdId)));
  }

  private void unassignTD(String tdId) {
    db.run(Update.entity(TransportationDemands_.class).data(TransportationDemands.FREIGHT_ORDER_ID, null)
        .where(t -> t.ID().eq(tdId)));
  }

  private List<FreightOrderStop> getFreightOrderStopsByFOId(String foId) {
    return db.run(Select.from(FreightOrderStop_.class).where(s -> s.freightOrder_ID().eq(foId)))
        .listOf(FreightOrderStop.class);
  }

  private List<FreightOrderStop> getOrderedFreightOrderStopsByFOId(String foId) {
    return db.run(
        Select.from(FreightOrderStop_.class).where(s -> s.freightOrder_ID().eq(foId)).orderBy(s -> s.sequence().asc()))
        .listOf(FreightOrderStop.class);
  }

  private FreightOrder getFoByIdOrThrow(String foId) {
    return db.run(Select.from(FreightOrder_.class).where(f -> f.ID().eq(foId))).first(FreightOrder.class)
        .orElseThrow(() -> new ServiceException(ErrorStatuses.NOT_FOUND, "Freight Order not found"));
  }

  private Optional<TransportationDemand> getTDByFoId(String foId) {
    return db.run(Select.from(TransportationDemand_.class).where(t -> t.freightOrder_ID().eq(foId))).first();
  }

  private Optional<TransportationDemand> getTDByFoIdAndToLocationId(String foId, String toLocationId) {
    return db.run(Select.from(TransportationDemand_.class)
        .where(t -> t.freightOrder_ID().eq(foId).and(t.toLocation_ID().eq(toLocationId)))).first();
  }

  private TransportationDemand getTDByIdOrThrow(String id) {
    return db.run(Select.from(TransportationDemands_.class).where(t -> t.ID().eq(id))).first(TransportationDemand.class)
        .orElseThrow(() -> new ServiceException(ErrorStatuses.NOT_FOUND, "Transportation Demand not found"));
  }

  private String generateDisplayId(String prefix) {
    return prefix + "-" + UUID.randomUUID().toString().replace("-", "").substring(0, 5).toUpperCase();
  }
}

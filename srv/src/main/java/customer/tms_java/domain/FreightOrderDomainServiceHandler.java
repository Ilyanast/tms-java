package customer.tms_java.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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

import cds.gen.freightorderdomainservice.FreightOrderDomainService_;
import cds.gen.freightorderdomainservice.FreightOrderStops_;
import cds.gen.freightorderdomainservice.FreightOrdersAssignTDContext;
import cds.gen.freightorderdomainservice.FreightOrdersSetStatusContext;
import cds.gen.freightorderdomainservice.FreightOrdersUnassignTDContext;
import cds.gen.tms.FreightOrder;
import cds.gen.tms.FreightOrder_;
import cds.gen.tms.FreightOrderItem;
import cds.gen.tms.FreightOrderItem_;
import cds.gen.tms.FreightOrderStop;
import cds.gen.tms.FreightOrderStop_;
import cds.gen.tms.TransportationDemand;
import cds.gen.tms.TransportationDemand_;
import cds.gen.tms.TransportationDemandItem;
import cds.gen.tms.TransportationDemandItem_;

@Component
@ServiceName(FreightOrderDomainService_.CDS_NAME)
public class FreightOrderDomainServiceHandler implements EventHandler {

  private final PersistenceService db;
  private final CqnAnalyzer cqnAnalyzer;

  private static final String IN_PLANNING = "IN_PLANNING";
  private static final String READY_FOR_EXECUTION = "READY_FOR_EXECUTION";
  private static final String IN_EXECUTION = "IN_EXECUTION";

  private static final Set<String> VALID_TRANSITIONS = Set.of(IN_PLANNING + "->" + READY_FOR_EXECUTION,
      READY_FOR_EXECUTION + "->" + IN_PLANNING, READY_FOR_EXECUTION + "->" + IN_EXECUTION);

  public FreightOrderDomainServiceHandler(PersistenceService db, CdsModel cdsModel) {
    this.db = db;
    this.cqnAnalyzer = CqnAnalyzer.create(cdsModel);
  }

  @On(event = FreightOrdersAssignTDContext.CDS_NAME)
  public void onAssignTd(FreightOrdersAssignTDContext ctx) {
    var freghtOrderId = getFOIdByCqnSelect(ctx.getCqn());

    var freightOrder = getFoByIdOrThrow(freghtOrderId);
    validateFOStatus(freightOrder, "assign TD");

    var transportationDemand = getTDByDisplayIdOrThrow(ctx.getTdDisplayId());
    validateTDIsNotAssigned(transportationDemand);

    var freightOrderStops = getFreightOrderStopsByFOId(freghtOrderId);
    if (freightOrderStops.isEmpty()) {
      createFreightOrderStop(freghtOrderId, transportationDemand.getFromLocationId(), 1);
      createFreightOrderStop(freghtOrderId, transportationDemand.getToLocationId(), 2);
    } else {
      validateFirstFoStop(freightOrderStops.get(0), transportationDemand.getFromLocationId());
      if (!freightOrderStops.stream().anyMatch(x -> x.getLocationId().equals(transportationDemand.getToLocationId()))) {
        createFreightOrderStop(freghtOrderId, transportationDemand.getToLocationId(), freightOrderStops.size() + 1);
      }
    }

    assignTDToFreightOrder(transportationDemand.getId(), freghtOrderId);

    var tdItems = getTDItemsByTdId(transportationDemand.getId());
    for (var tdItem : tdItems) {
      createFreightOrderItem(mapTDItemToFOItem(tdItem, freghtOrderId));
    }

    ctx.setCompleted();
  }

  @On(event = FreightOrdersUnassignTDContext.CDS_NAME)
  public void unassignTd(FreightOrdersUnassignTDContext ctx) {
    var freghtOrderId = getFOIdByCqnSelect(ctx.getCqn());

    var freightOrder = getFoByIdOrThrow(freghtOrderId);
    validateFOStatus(freightOrder, "unassign TD");

    var transportationDemand = getTDByIdOrThrow(ctx.getTdId());
    validateTDIsAssigned(transportationDemand, freghtOrderId);

    var tdItems = getTDItemsByTdId(transportationDemand.getId());
    for (var tdItem : tdItems) {
      deleteFreightOrderItemByTdId(tdItem.getId());
    }

    unassignTD(ctx.getTdId());

    if (getTDByFoIdAndToLocationId(freghtOrderId, transportationDemand.getToLocationId()).isEmpty()) {
      deleteFreightOrderStop(freghtOrderId, transportationDemand.getToLocationId());
      reorderFOStops(freghtOrderId);
    }

    if (getTDByFoId(freghtOrderId).isEmpty()) {
      deleteFreightOrderStopByFoId(freghtOrderId);
    }

    ctx.setCompleted();
  }

  @On(event = FreightOrdersSetStatusContext.CDS_NAME)
  public void setStatus(FreightOrdersSetStatusContext ctx) {
    var freightOrderId = getFOIdByCqnSelect(ctx.getCqn());

    var freightOrder = getFoByIdOrThrow(freightOrderId);
    if (freightOrder.getStatusCode().equals(ctx.getNewStatusCode())) {
      ctx.setCompleted();
      return;
    }

    validateFoStatusTransition(freightOrder.getStatusCode(), ctx.getNewStatusCode());
    updateFoStatusCode(freightOrderId, ctx.getNewStatusCode());

    ctx.setCompleted();
  }

  private void reorderFOStops(String foId) {
    var foStops = getOrderedFreightOrderStopsByFOId(foId);
    for (int i = 0; i < foStops.size(); i++) {
      var foStop = foStops.get(i);
      if (foStop.getSequence() != i + 1) {
        updateFOStopSequence(foId, i + 1);
      }
    }
  }

  private void createFreightOrderStop(String foId, String locationId, int sequence) {
    FreightOrderStop freightOrderStop = FreightOrderStop.create();

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

  private void validateTDIsAssigned(TransportationDemand transportationDemand, String foId) {
    if (!foId.equals(transportationDemand.getFreightOrderId())) {
      throw new ServiceException(ErrorStatuses.BAD_REQUEST, "TD is not assigned to this Freight Order");
    }
  }

  private void validateTDIsNotAssigned(TransportationDemand transportationDemand) {
    if (transportationDemand.getFreightOrderId() != null) {
      throw new ServiceException(ErrorStatuses.CONFLICT, "Transportation Demand already assigned");
    }
  }

  private void validateFOStatus(FreightOrder freightOrder, String action) {
    if (!IN_PLANNING.equals(freightOrder.getStatusCode())) {
      throw new ServiceException(ErrorStatuses.CONFLICT, "Cannot " + action + ": Freight Order must be IN_PLANNING");
    }
  }

  private void validateFoStatusTransition(String currentStatus, String newStatus) {
    if (!VALID_TRANSITIONS.contains(currentStatus + "->" + newStatus)) {
      throw new ServiceException(ErrorStatuses.CONFLICT, "Invalid status transition");
    }
  }

  private String getFOIdByCqnSelect(CqnSelect cqn) {
    return (String) cqnAnalyzer.analyze(cqn).targetKeys().get(FreightOrder_.ID);
  }

  private void createFreightOrderItem(FreightOrderItem item) {
    db.run(Insert.into(FreightOrderItem_.class).entry(item));
  }

  private void deleteFreightOrderItemByTdId(String tdItemId) {
    db.run(Delete.from(FreightOrderItem_.class).where(x -> x.transportationDemandItem_ID().eq(tdItemId)));
  }

  private FreightOrder updateFoStatusCode(String foId, String statusCode) {
    return db
        .run(Update.entity(FreightOrder_.class).data(FreightOrder.STATUS_CODE, statusCode).where(f -> f.ID().eq(foId)))
        .first(FreightOrder.class).orElseThrow();
  }

  private FreightOrderStop updateFOStopSequence(String foStopId, int sequence) {
    return db.run(Update.entity(FreightOrderStop_.class).data(FreightOrderStop.SEQUENCE, sequence)
        .where(s -> s.ID().eq(foStopId))).first(FreightOrderStop.class).orElseThrow();
  }

  private void deleteFreightOrderStop(String foId, String locationId) {
    db.run(Delete.from(FreightOrderStop_.class)
        .where(s -> s.freightOrder_ID().eq(foId).and(s.location_ID().eq(locationId))));
  }

  private void deleteFreightOrderStopByFoId(String foId) {
    db.run(Delete.from(FreightOrderStop_.class).where(s -> s.freightOrder_ID().eq(foId)));
  }

  private List<TransportationDemandItem> getTDItemsByTdId(String tdId) {
    return db.run(Select.from(TransportationDemandItem_.class).where(x -> x.transportationDemand_ID().eq(tdId)))
        .listOf(TransportationDemandItem.class);
  }

  private void assignTDToFreightOrder(String transportationDemaninId, String FreightOrderId) {
    db.run(Update.entity(TransportationDemand_.class).data(TransportationDemand.FREIGHT_ORDER_ID, FreightOrderId)
        .where(x -> x.ID().eq(transportationDemaninId)));
  }

  private void unassignTD(String transportationDemaninId) {
    db.run(Update.entity(TransportationDemand_.class).data(TransportationDemand.FREIGHT_ORDER_ID, null)
        .where(x -> x.ID().eq(transportationDemaninId)));
  }

  private List<FreightOrderStop> getFreightOrderStopsByFOId(String foId) {
    return db.run(Select.from(FreightOrderStops_.class).where(x -> x.freightOrder_ID().eq(foId)))
        .listOf(FreightOrderStop.class);
  }

  private List<FreightOrderStop> getOrderedFreightOrderStopsByFOId(String foId) {
    return db.run(
        Select.from(FreightOrderStops_.class).where(x -> x.freightOrder_ID().eq(foId)).orderBy(s -> s.sequence().asc()))
        .listOf(FreightOrderStop.class);
  }

  private FreightOrder getFoByIdOrThrow(String foId) {
    return db.run(Select.from(FreightOrder_.class).where(x -> x.ID().eq(foId))).first(FreightOrder.class)
        .orElseThrow(() -> new ServiceException(ErrorStatuses.NOT_FOUND, "Freight Order not found"));
  }

  private Optional<TransportationDemand> getTDByFoId(String foId) {
    return db.run(Select.from(TransportationDemand_.class).where(t -> t.freightOrder_ID().eq(foId))).first();
  }

  private Optional<TransportationDemand> getTDByFoIdAndToLocationId(String foId, String toLocationId) {
    return db.run(Select.from(TransportationDemand_.class)
        .where(t -> t.freightOrder_ID().eq(foId).and(t.toLocation_ID().eq(toLocationId)))).first();
  }

  private TransportationDemand getTDByDisplayIdOrThrow(String displayId) {
    return db.run(Select.from(TransportationDemand_.class).where(x -> x.displayId().eq(displayId)))
        .first(TransportationDemand.class)
        .orElseThrow(() -> new ServiceException(ErrorStatuses.NOT_FOUND, "Transportation Demand not found"));
  }

  private TransportationDemand getTDByIdOrThrow(String id) {
    return db.run(Select.from(TransportationDemand_.class).where(x -> x.ID().eq(id))).first(TransportationDemand.class)
        .orElseThrow(() -> new ServiceException(ErrorStatuses.NOT_FOUND, "Transportation Demand not found"));
  }

  private String generateDisplayId(String prefix) {
    return prefix + "-" + UUID.randomUUID().toString().replace("-", "").substring(0, 5).toUpperCase();
  }
}

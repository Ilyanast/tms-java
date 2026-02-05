package customer.tms_java.handlers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.cds.ql.Delete;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.cds.CdsDeleteEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.draft.DraftEditEventContext;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.freightorderservice.FreightOrderService_;
import cds.gen.freightorderservice.FreightOrders;
import cds.gen.freightorderservice.FreightOrders_;
import cds.gen.freightorderservice.FreightOrdersAssignTDContext;
import cds.gen.freightorderservice.FreightOrdersSetStatusContext;
import cds.gen.freightorderservice.FreightOrdersUnassignTDContext;
import cds.gen.tms.FreightOrderItem;
import cds.gen.tms.FreightOrderItem_;
import cds.gen.tms.TransportationDemand;
import cds.gen.tms.TransportationDemandItem;
import cds.gen.tms.TransportationDemandItem;
import cds.gen.tms.TransportationDemandItem_;
import cds.gen.tms.TransportationDemand_;

@Component
@ServiceName(FreightOrderService_.CDS_NAME)
public class FreightOrderServiceHandler implements EventHandler {

        private static final String IN_PLANNING = "IN_PLANNING";
        private static final String READY_FOR_EXECUTION = "READY_FOR_EXECUTION";
        private static final String IN_EXECUTION = "IN_EXECUTION";

        private static final Set<String> VALID_TRANSITIONS = Set.of(
                        IN_PLANNING + "->" + READY_FOR_EXECUTION,
                        READY_FOR_EXECUTION + "->" + IN_PLANNING,
                        READY_FOR_EXECUTION + "->" + IN_EXECUTION);

        @Autowired
        private PersistenceService db;

        @Autowired
        private CdsModel cdsModel;

        @Before(event = DraftService.EVENT_DRAFT_EDIT, entity = FreightOrders_.CDS_NAME)
        public void blockDraftEdit(DraftEditEventContext context) {
                throw new ServiceException(ErrorStatuses.METHOD_NOT_ALLOWED,
                                "Standard edit is not supported. Use actions to modify Freight Order.");
        }

        @Before(event = CqnService.EVENT_DELETE, entity = FreightOrders_.CDS_NAME)
        public void validateNoAssignedTDs(CdsDeleteEventContext context) {
                CqnAnalyzer analyzer = CqnAnalyzer.create(cdsModel);
                Map<String, Object> keys = analyzer.analyze(context.getCqn()).targetKeys();
                String foId = (String) keys.get(FreightOrders.ID);

                if (foId == null) {
                        return;
                }

                long count = db.run(
                                Select.from(TransportationDemand_.class)
                                                .where(td -> td.freightOrder_ID().eq(foId)))
                                .rowCount();

                if (count > 0) {
                        throw new ServiceException(ErrorStatuses.CONFLICT,
                                        "Cannot delete Freight Order with assigned Transportation Demands. Please unassign all TDs first.");
                }
        }

        @On(event = FreightOrdersAssignTDContext.CDS_NAME, entity = FreightOrders_.CDS_NAME)
        public void onAssignTD(FreightOrdersAssignTDContext context) {
                CqnAnalyzer analyzer = CqnAnalyzer.create(cdsModel);
                Map<String, Object> keys = analyzer.analyze(context.getCqn()).targetKeys();
                String foId = (String) keys.get(FreightOrders.ID);

                FreightOrders fo = findFo(foId);
                requireInPlanningStatus(fo, "assign TD");

                String tdDisplayId = context.getTdDisplayId();
                if (tdDisplayId == null || tdDisplayId.isBlank()) {
                        throw new ServiceException(ErrorStatuses.BAD_REQUEST,
                                        "TD Display ID is required");
                }

                TransportationDemand td = db.run(
                                Select.from(TransportationDemand_.class)
                                                .where(t -> t.displayId().eq(tdDisplayId)))
                                .first(TransportationDemand.class)
                                .orElseThrow(() -> new ServiceException(ErrorStatuses.NOT_FOUND,
                                                "Transportation Demand with ID '" + tdDisplayId + "' not found"));

                if (td.getFreightOrderId() != null) {
                        throw new ServiceException(ErrorStatuses.CONFLICT,
                                        "Transportation Demand '" + tdDisplayId
                                                        + "' is already assigned to a Freight Order");
                }

                db.run(Update.entity(TransportationDemand_.class)
                                .data(TransportationDemand.FREIGHT_ORDER_ID, foId)
                                .where(t -> t.ID().eq(td.getId())));

                List<TransportationDemandItem> tdItems = db.run(
                                Select.from(TransportationDemandItem_.class)
                                                .where(i -> i.transportationDemand_ID().eq(td.getId())))
                                .listOf(TransportationDemandItem.class);

                for (TransportationDemandItem tdItem : tdItems) {
                        FreightOrderItem foItem = FreightOrderItem.create();
                        foItem.setId(UUID.randomUUID().toString());
                        foItem.setFreightOrderId(foId);
                        foItem.setProductName(tdItem.getProductName());
                        foItem.setQuantity(tdItem.getQuantity());
                        foItem.setTransportationDemandItemId(tdItem.getId());

                        db.run(Insert.into(FreightOrderItem_.class).entry(foItem));
                }

                context.setResult(findFo(foId));
        }

        @On(event = FreightOrdersUnassignTDContext.CDS_NAME, entity = FreightOrders_.CDS_NAME)
        public void onUnassignTD(FreightOrdersUnassignTDContext context) {
                CqnAnalyzer analyzer = CqnAnalyzer.create(cdsModel);
                Map<String, Object> keys = analyzer.analyze(context.getCqn()).targetKeys();
                String foId = (String) keys.get(FreightOrders.ID);
                String tdId = context.getTdId();

                FreightOrders fo = findFo(foId);
                requireInPlanningStatus(fo, "unassign TD");

                TransportationDemand td = db.run(
                                Select.from(TransportationDemand_.class).where(t -> t.ID().eq(tdId)))
                                .first(TransportationDemand.class)
                                .orElseThrow(() -> new ServiceException(ErrorStatuses.NOT_FOUND,
                                                "Transportation Demand not found"));

                if (!foId.equals(td.getFreightOrderId())) {
                        throw new ServiceException(ErrorStatuses.BAD_REQUEST,
                                        "Transportation Demand is not assigned to this Freight Order");
                }

                List<TransportationDemandItem> tdItems = db.run(
                                Select.from(TransportationDemandItem_.class)
                                                .where(i -> i.transportationDemand_ID().eq(tdId)))
                                .listOf(TransportationDemandItem.class);

                for (TransportationDemandItem tdItem : tdItems) {
                        db.run(Delete.from(FreightOrderItem_.class)
                                        .where(i -> i.transportationDemandItem_ID().eq(tdItem.getId())));
                }

                db.run(Update.entity(TransportationDemand_.class)
                                .data(TransportationDemand.FREIGHT_ORDER_ID, null)
                                .where(t -> t.ID().eq(tdId)));

                context.setResult(findFo(foId));
        }

        @On(event = FreightOrdersSetStatusContext.CDS_NAME, entity = FreightOrders_.CDS_NAME)
        public void onSetStatus(FreightOrdersSetStatusContext context) {
                CqnAnalyzer analyzer = CqnAnalyzer.create(cdsModel);
                Map<String, Object> keys = analyzer.analyze(context.getCqn()).targetKeys();
                String foId = (String) keys.get(FreightOrders.ID);
                String newStatus = context.getNewStatusCode();

                if (newStatus == null || newStatus.isBlank()) {
                        throw new ServiceException(ErrorStatuses.BAD_REQUEST, "New status is required");
                }

                FreightOrders fo = findFo(foId);
                String currentStatus = fo.getStatusCode();

                if (currentStatus.equals(newStatus)) {
                        context.setResult(fo);
                        return;
                }

                String transition = currentStatus + "->" + newStatus;

                if (!VALID_TRANSITIONS.contains(transition)) {
                        throw new ServiceException(ErrorStatuses.CONFLICT,
                                        "Invalid status transition from '" + currentStatus + "' to '" + newStatus
                                                        + "'");
                }

                db.run(Update.entity(FreightOrders_.class)
                                .data(FreightOrders.STATUS_CODE, newStatus)
                                .where(f -> f.ID().eq(foId)));

                context.setResult(findFo(foId));
        }

        private FreightOrders findFo(String id) {
                return db.run(
                                Select.from(FreightOrders_.class)
                                                .columns(f -> f._all(), f -> f.status_code())
                                                .where(f -> f.ID().eq(id)))
                                .single(FreightOrders.class);
        }

        private void requireInPlanningStatus(FreightOrders fo, String action) {
                if (!IN_PLANNING.equals(fo.getStatusCode())) {
                        throw new ServiceException(ErrorStatuses.CONFLICT,
                                        "Cannot " + action + ": Freight Order must be in 'In Planning' status");
                }
        }
}

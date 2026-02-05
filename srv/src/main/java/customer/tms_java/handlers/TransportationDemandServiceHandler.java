package customer.tms_java.handlers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.cds.ql.Delete;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.cds.CdsDeleteEventContext;
import com.sap.cds.services.cds.CdsUpdateEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.draft.DraftEditEventContext;
import com.sap.cds.services.draft.DraftSaveEventContext;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.tms.FreightOrderItem;
import cds.gen.tms.FreightOrderItem_;
import cds.gen.tms.TransportationDemandItem;
import cds.gen.tms.TransportationDemandItem_;
import cds.gen.transportationdemandservice.TransportationDemandService_;
import cds.gen.transportationdemandservice.TransportationDemands;
import cds.gen.transportationdemandservice.TransportationDemands_;
import cds.gen.transportationdemandservice.TransportationDemandsAssignContext;
import cds.gen.transportationdemandservice.TransportationDemandsUnassignContext;

@Component
@ServiceName(TransportationDemandService_.CDS_NAME)
public class TransportationDemandServiceHandler implements EventHandler {

    @Autowired
    private PersistenceService db;

    @Autowired
    private CdsModel cdsModel;

    @Before(event = DraftService.EVENT_DRAFT_EDIT, entity = TransportationDemands_.CDS_NAME)
    public void validateBeforeDraftEdit(DraftEditEventContext context, TransportationDemands td) {
        if (td == null || td.getId() == null) {
            return;
        }
        validateNotAssigned(td.getId(), "edit");
    }

    @Before(event = DraftService.EVENT_DRAFT_SAVE, entity = TransportationDemands_.CDS_NAME)
    public void validateBeforeDraftSave(DraftSaveEventContext context, TransportationDemands td) {
        if (td == null || td.getId() == null) {
            return;
        }
        validateNotAssigned(td.getId(), "save changes to");
    }

    @Before(event = CqnService.EVENT_UPDATE, entity = TransportationDemands_.CDS_NAME)
    public void validateBeforeUpdate(CdsUpdateEventContext context) {
        CqnAnalyzer analyzer = CqnAnalyzer.create(cdsModel);
        Map<String, Object> keys = analyzer.analyze(context.getCqn()).targetKeys();
        String tdId = (String) keys.get(TransportationDemands.ID);

        if (tdId == null) {
            return;
        }
        validateNotAssigned(tdId, "modify");
    }

    @Before(event = CqnService.EVENT_DELETE, entity = TransportationDemands_.CDS_NAME)
    public void validateBeforeDelete(CdsDeleteEventContext context) {
        CqnAnalyzer analyzer = CqnAnalyzer.create(cdsModel);
        Map<String, Object> keys = analyzer.analyze(context.getCqn()).targetKeys();
        String tdId = (String) keys.get(TransportationDemands.ID);

        if (tdId == null) {
            return;
        }
        validateNotAssigned(tdId, "delete");
    }

    private void validateNotAssigned(String tdId, String action) {
        TransportationDemands existing = findById(tdId);

        if (existing != null && existing.getFreightOrderId() != null) {
            throw new ServiceException(ErrorStatuses.CONFLICT,
                    "Cannot " + action
                            + " Transportation Demand that is assigned to a Freight Order. Please unassign it first.");
        }
    }

    @On(event = TransportationDemandsAssignContext.CDS_NAME, entity = TransportationDemands_.CDS_NAME)
    public void onAssign(TransportationDemandsAssignContext context) {
        String tdId = extractEntityId(context.getCqn());
        String foId = context.getFreightOrderId();

        TransportationDemands existing = findById(tdId);
        if (existing.getFreightOrderId() != null) {
            throw new ServiceException(ErrorStatuses.CONFLICT,
                    "Transportation Demand is already assigned to a Freight Order");
        }

        db.run(Update.entity(TransportationDemands_.class)
                .data(TransportationDemands.FREIGHT_ORDER_ID, foId)
                .where(t -> t.ID().eq(tdId)));

        context.setResult(findById(tdId)); 
    }

    @On(event = TransportationDemandsUnassignContext.CDS_NAME, entity = TransportationDemands_.CDS_NAME)
    public void onUnassign(TransportationDemandsUnassignContext context) {
        String tdId = extractEntityId(context.getCqn());

        TransportationDemands existing = findById(tdId);
        if (existing.getFreightOrderId() == null) {
            throw new ServiceException(ErrorStatuses.BAD_REQUEST,
                    "Transportation Demand is not assigned to any Freight Order");
        }

        List<TransportationDemandItem> tdItems = db.run(
                Select.from(TransportationDemandItem_.class)
                        .where(i -> i.transportationDemand_ID().eq(tdId)))
                .listOf(TransportationDemandItem.class);

        for (TransportationDemandItem tdItem : tdItems) {
            db.run(Delete.from(FreightOrderItem_.class)
                    .where(i -> i.transportationDemandItem_ID().eq(tdItem.getId())));
        }

        db.run(Update.entity(TransportationDemands_.class)
                .data(TransportationDemands.FREIGHT_ORDER_ID, null)
                .where(t -> t.ID().eq(tdId)));

        context.setResult(findById(tdId));
    }

    private TransportationDemands findById(String id) {
        return db.run(Select.from(TransportationDemands_.class)
                .where(t -> t.ID().eq(id)))
                .single(TransportationDemands.class);
    }

    private String extractEntityId(com.sap.cds.ql.cqn.CqnSelect cqn) {
        CqnAnalyzer analyzer = CqnAnalyzer.create(cdsModel);
        Map<String, Object> keys = analyzer.analyze(cqn).targetKeys();
        return (String) keys.get(TransportationDemands.ID);
    }
}

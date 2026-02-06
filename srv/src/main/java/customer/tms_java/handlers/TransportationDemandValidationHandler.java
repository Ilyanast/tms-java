package customer.tms_java.handlers;

import org.springframework.stereotype.Component;

import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnDelete;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.cds.CdsDeleteEventContext;
import com.sap.cds.services.cds.CdsUpdateEventContext;
import com.sap.cds.services.draft.DraftEditEventContext;
import com.sap.cds.services.draft.DraftSaveEventContext;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.transportationdemanduiservice.TransportationDemandUIService_;
import cds.gen.transportationdemanduiservice.TransportationDemands;
import cds.gen.transportationdemanduiservice.TransportationDemands_;

@Component
@ServiceName(TransportationDemandUIService_.CDS_NAME)
public class TransportationDemandValidationHandler implements EventHandler {

    private final PersistenceService db;
    private final CqnAnalyzer analyzer;

    public TransportationDemandValidationHandler(PersistenceService db, CdsModel model) {
        this.db = db;
        this.analyzer = CqnAnalyzer.create(model);
    }

    @Before(event = DraftService.EVENT_DRAFT_EDIT, entity = TransportationDemands_.CDS_NAME)
    public void beforeDraftEdit(DraftEditEventContext ctx, TransportationDemands td) {
        validate(td != null ? td.getId() : null);
    }

    @Before(event = DraftService.EVENT_DRAFT_SAVE, entity = TransportationDemands_.CDS_NAME)
    public void beforeDraftSave(DraftSaveEventContext ctx, TransportationDemands td) {
        validate(td != null ? td.getId() : null);
    }

    @Before(event = com.sap.cds.services.cds.CqnService.EVENT_UPDATE, entity = TransportationDemands_.CDS_NAME)
    public void beforeUpdate(CdsUpdateEventContext ctx) {
        validate(extractId(ctx.getCqn()));
    }

    @Before(event = com.sap.cds.services.cds.CqnService.EVENT_DELETE, entity = TransportationDemands_.CDS_NAME)
    public void beforeDelete(CdsDeleteEventContext ctx) {
        validate(extractId(ctx.getCqn()));
    }

    private void validate(String tdId) {
        if (tdId == null) {
            return;
        }

        TransportationDemands td = db.run(
                Select.from(TransportationDemands_.class)
                        .columns(t -> t.freightOrder())
                        .where(t -> t.ID().eq(tdId)))
                .single(TransportationDemands.class);

        if (td != null && td.getFreightOrder() != null) {
            throw new ServiceException(
                    ErrorStatuses.CONFLICT,
                    "Transportation Demand is assigned to a Freight Order. Unassign it first.");
        }
    }

    private String extractId(CqnUpdate cqn) {
        return (String) analyzer.analyze(cqn)
                .targetKeys()
                .get(TransportationDemands.ID);
    }

    private String extractId(CqnDelete cqn) {
        return (String) analyzer.analyze(cqn)
                .targetKeys()
                .get(TransportationDemands.ID);
    }
}

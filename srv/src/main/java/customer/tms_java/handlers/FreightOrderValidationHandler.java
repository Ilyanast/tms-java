package customer.tms_java.handlers;

import org.springframework.stereotype.Component;

import com.sap.cds.ql.Select;
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
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.freightorderuiservice.FreightOrderUIService_;
import cds.gen.freightorderuiservice.FreightOrders;
import cds.gen.freightorderuiservice.FreightOrders_;
import cds.gen.tms.TransportationDemand_;

@Component
@ServiceName(FreightOrderUIService_.CDS_NAME)
public class FreightOrderValidationHandler implements EventHandler {

    private final PersistenceService db;
    private final CqnAnalyzer analyzer;

    public FreightOrderValidationHandler(PersistenceService db, CdsModel model) {
        this.db = db;
        this.analyzer = CqnAnalyzer.create(model);
    }

    @Before(event = DraftService.EVENT_DRAFT_EDIT, entity = FreightOrders_.CDS_NAME)
    public void blockDraftEdit(DraftEditEventContext ctx) {
        throw new ServiceException(
                ErrorStatuses.METHOD_NOT_ALLOWED,
                "Standard edit is not supported. Use actions to modify Freight Order.");
    }

    @Before(event = CqnService.EVENT_DELETE, entity = FreightOrders_.CDS_NAME)
    public void validateNoAssignedTds(CdsDeleteEventContext ctx) {
        String foId = (String) analyzer.analyze(ctx.getCqn()).targetKeys().get(FreightOrders.ID);
        if (foId == null) {
            return;
        }

        long count = db.run(
                Select.from(TransportationDemand_.class)
                        .where(td -> td.freightOrder_ID().eq(foId)))
                .rowCount();

        if (count > 0) {
            throw new ServiceException(
                    ErrorStatuses.CONFLICT,
                    "Cannot delete Freight Order with assigned Transportation Demands");
        }
    }
}

package customer.tms_java.handlers;

import org.springframework.stereotype.Component;

import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.freightorderuiservice.FreightOrderUIService_;
import cds.gen.freightorderuiservice.FreightOrders;
import cds.gen.freightorderuiservice.FreightOrders_;
import cds.gen.freightorderuiservice.FreightOrdersAssignTDContext;
import cds.gen.freightorderuiservice.FreightOrdersSetStatusContext;
import cds.gen.freightorderuiservice.FreightOrdersUnassignTDContext;
import customer.tms_java.domain.FreightOrderDomainService;

@Component
@ServiceName(FreightOrderUIService_.CDS_NAME)
public class FreightOrderCommandHandler implements EventHandler {

    private final FreightOrderDomainService domain;
    private final PersistenceService db;
    private final CqnAnalyzer analyzer;

    public FreightOrderCommandHandler(
            FreightOrderDomainService domain,
            PersistenceService db,
            CdsModel model) {
        this.domain = domain;
        this.db = db;
        this.analyzer = CqnAnalyzer.create(model);
    }

    @On(event = FreightOrdersAssignTDContext.CDS_NAME)
    public void assignTd(FreightOrdersAssignTDContext ctx) {
        String foId = extractId(ctx.getCqn());

        domain.assignTd(foId, ctx.getTdDisplayId());

        ctx.setResult(loadFo(foId));
    }

    @On(event = FreightOrdersUnassignTDContext.CDS_NAME)
    public void unassignTd(FreightOrdersUnassignTDContext ctx) {
        String foId = extractId(ctx.getCqn());

        domain.unassignTd(foId, ctx.getTdId());

        ctx.setResult(loadFo(foId));
    }

    @On(event = FreightOrdersSetStatusContext.CDS_NAME)
    public void setStatus(FreightOrdersSetStatusContext ctx) {
        String foId = extractId(ctx.getCqn());

        domain.setStatus(foId, ctx.getNewStatusCode());

        ctx.setResult(loadFo(foId));
    }

    private FreightOrders loadFo(String id) {
        return db.run(
                Select.from(FreightOrders_.class)
                        .where(f -> f.ID().eq(id)))
                .single(FreightOrders.class);
    }

    private String extractId(CqnSelect cqn) {
        return (String) analyzer.analyze(cqn).targetKeys().get(FreightOrders.ID);
    }
}

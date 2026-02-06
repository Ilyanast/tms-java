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

import cds.gen.transportationdemanduiservice.TransportationDemandUIService_;
import cds.gen.transportationdemanduiservice.TransportationDemands;
import cds.gen.transportationdemanduiservice.TransportationDemands_;
import cds.gen.transportationdemanduiservice.TransportationDemandsAssignContext;
import cds.gen.transportationdemanduiservice.TransportationDemandsUnassignContext;
import customer.tms_java.domain.TransportationDemandDomainService;

@Component
@ServiceName(TransportationDemandUIService_.CDS_NAME)
public class TransportationDemandCommandHandler implements EventHandler {

    private final TransportationDemandDomainService domain;
    private final PersistenceService db;
    private final CqnAnalyzer analyzer;

    public TransportationDemandCommandHandler(
            TransportationDemandDomainService domain,
            PersistenceService db,
            CdsModel model) {
        this.db = db;
        this.domain = domain;
        this.analyzer = CqnAnalyzer.create(model);
    }

    @On(event = TransportationDemandsAssignContext.CDS_NAME)
    public void assign(TransportationDemandsAssignContext ctx) {
        String tdId = extractId(ctx.getCqn());

        domain.assign(tdId, ctx.getFreightOrderId());

        ctx.setResult(load(tdId));
    }

    @On(event = TransportationDemandsUnassignContext.CDS_NAME)
    public void unassign(TransportationDemandsUnassignContext ctx) {
        String tdId = extractId(ctx.getCqn());

        domain.unassign(tdId);

        ctx.setResult(load(tdId));
    }

    private TransportationDemands load(String tdId) {
        return db.run(
                Select.from(TransportationDemands_.class)
                        .where(t -> t.ID().eq(tdId)))
                .single(TransportationDemands.class);
    }

    private String extractId(CqnSelect cqn) {
        return (String) analyzer.analyze(cqn).targetKeys().get(TransportationDemands.ID);
    }
}
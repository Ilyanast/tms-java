package customer.tms_java.handlers;

import org.springframework.stereotype.Component;

import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.transportationdemanduiservice.TransportationDemandUIService_;
import cds.gen.transportationdemanduiservice.TransportationDemands;
import customer.tms_java.domain.TransportationDemandDomainService;
import customer.tms_java.ui.TransportationDemandUIService;

@Component
@ServiceName(TransportationDemandUIService_.CDS_NAME)
public class TransportationDemandUIServiceHandler implements EventHandler {

  private final TransportationDemandDomainService domainService;
  private final TransportationDemandUIService transportationDemandUIService;
  private final CqnAnalyzer analyzer;

  public TransportationDemandUIServiceHandler(TransportationDemandDomainService domainService,
      TransportationDemandUIService uiService, CdsModel model) {
    this.domainService = domainService;
    this.transportationDemandUIService = uiService;
    this.analyzer = CqnAnalyzer.create(model);
  }

  @On(event = cds.gen.transportationdemanduiservice.TransportationDemandsAssignContext.CDS_NAME)
  public void assign(cds.gen.transportationdemanduiservice.TransportationDemandsAssignContext ctx) {
    String tdId = extractId(ctx.getCqn());

    domainService.assign(tdId, ctx.getFreightOrderId());

    ctx.setResult(transportationDemandUIService.getTDById(tdId));
  }

  @On(event = cds.gen.transportationdemanduiservice.TransportationDemandsUnassignContext.CDS_NAME)
  public void unassign(cds.gen.transportationdemanduiservice.TransportationDemandsUnassignContext ctx) {
    String tdId = extractId(ctx.getCqn());

    domainService.unassign(tdId);

    ctx.setResult(transportationDemandUIService.getTDById(tdId));
  }

  private String extractId(CqnSelect cqn) {
    return (String) analyzer.analyze(cqn).targetKeys().get(TransportationDemands.ID);
  }
}

package customer.tms_java.handlers;

import org.springframework.stereotype.Component;

import com.sap.cds.services.EventContext;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.transportationdemanddomainservice.TransportationDemandDomainService;
import cds.gen.transportationdemanduiservice.TransportationDemandUIService_;
import cds.gen.transportationdemanduiservice.TransportationDemandsAssignContext;
import cds.gen.transportationdemanduiservice.TransportationDemandsUnassignContext;

@Component
@ServiceName(TransportationDemandUIService_.CDS_NAME)
public class TransportationDemandUIServiceHandler implements EventHandler {

  private final TransportationDemandDomainService transportationDemandDomainService;

  public TransportationDemandUIServiceHandler(TransportationDemandDomainService transportationDemandDomainService) {
    this.transportationDemandDomainService = transportationDemandDomainService;
  }

  @On(event = TransportationDemandsAssignContext.CDS_NAME)
  public void assign(TransportationDemandsAssignContext ctx) {
    delegateToDomain(ctx);
  }

  @On(event = TransportationDemandsUnassignContext.CDS_NAME)
  public void unassign(TransportationDemandsUnassignContext ctx) {
    delegateToDomain(ctx);
  }

  private void delegateToDomain(EventContext sourceCtx) {
    EventContext delegateContext = EventContext.create(sourceCtx.getEvent(), (String) null);

    sourceCtx.keySet().forEach(key -> delegateContext.put(key, sourceCtx.get(key)));

    transportationDemandDomainService.emit(delegateContext);

    if (delegateContext.isCompleted()) {
      sourceCtx.put("result", delegateContext.get("result"));
      sourceCtx.setCompleted();
    }
  }
}

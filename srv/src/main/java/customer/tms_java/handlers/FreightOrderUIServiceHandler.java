package customer.tms_java.handlers;

import org.springframework.stereotype.Component;

import com.sap.cds.services.EventContext;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.freightorderdomainservice.FreightOrderDomainService;
import cds.gen.freightorderuiservice.FreightOrderUIService_;
import cds.gen.freightorderuiservice.FreightOrdersAssignTDContext;
import cds.gen.freightorderuiservice.FreightOrdersSetStatusContext;
import cds.gen.freightorderuiservice.FreightOrdersUnassignTDContext;

@Component
@ServiceName(FreightOrderUIService_.CDS_NAME)
public class FreightOrderUIServiceHandler implements EventHandler {

  private final FreightOrderDomainService freightOrderDomainService;

  public FreightOrderUIServiceHandler(FreightOrderDomainService freightOrderDomainService) {
    this.freightOrderDomainService = freightOrderDomainService;
  }

  @On(event = FreightOrdersAssignTDContext.CDS_NAME)
  public void assignTd(FreightOrdersAssignTDContext ctx) {
    delegateToDomain(ctx);
  }

  @On(event = FreightOrdersUnassignTDContext.CDS_NAME)
  public void unassignTd(FreightOrdersUnassignTDContext ctx) {
    delegateToDomain(ctx);
  }

  @On(event = FreightOrdersSetStatusContext.CDS_NAME)
  public void setStatus(FreightOrdersSetStatusContext ctx) {
    delegateToDomain(ctx);
  }

  private void delegateToDomain(EventContext sourceCtx) {
    EventContext delegateContext = EventContext.create(sourceCtx.getEvent(), (String) null);

    sourceCtx.keySet().forEach(key -> delegateContext.put(key, sourceCtx.get(key)));

    freightOrderDomainService.emit(delegateContext);

    if (delegateContext.isCompleted()) {
      sourceCtx.put("result", delegateContext.get("result"));
      sourceCtx.setCompleted();
    }
  }
}

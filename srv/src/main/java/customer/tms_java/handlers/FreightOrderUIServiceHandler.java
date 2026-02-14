package customer.tms_java.handlers;

import org.springframework.stereotype.Component;

import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.draft.DraftEditEventContext;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.freightorderuiservice.FreightOrderUIService_;
import cds.gen.freightorderuiservice.FreightOrders;
import customer.tms_java.domain.FreightOrderDomainService;
import customer.tms_java.ui.FreightOrderUIService;

@Component
@ServiceName(FreightOrderUIService_.CDS_NAME)
public class FreightOrderUIServiceHandler implements EventHandler {

  private final FreightOrderDomainService domainService;
  private final FreightOrderUIService freightOrderUIService;
  private final CqnAnalyzer analyzer;

  public FreightOrderUIServiceHandler(FreightOrderDomainService domainService, FreightOrderUIService uiService,
      CdsModel model) {
    this.domainService = domainService;
    this.freightOrderUIService = uiService;
    this.analyzer = CqnAnalyzer.create(model);
  }

  @On(event = cds.gen.freightorderuiservice.FreightOrdersAssignTDContext.CDS_NAME)
  public void assignTd(cds.gen.freightorderuiservice.FreightOrdersAssignTDContext ctx) {
    String foId = extractId(ctx.getCqn());

    domainService.assignTd(foId, ctx.getTdDisplayId());

    ctx.setResult(freightOrderUIService.getFOById(foId));
  }

  @On(event = cds.gen.freightorderuiservice.FreightOrdersUnassignTDContext.CDS_NAME)
  public void unassignTd(cds.gen.freightorderuiservice.FreightOrdersUnassignTDContext ctx) {
    String foId = extractId(ctx.getCqn());

    domainService.unassignTd(foId, ctx.getTdId());

    ctx.setResult(freightOrderUIService.getFOById(foId));
  }

  @On(event = cds.gen.freightorderuiservice.FreightOrdersSetStatusContext.CDS_NAME)
  public void setStatus(cds.gen.freightorderuiservice.FreightOrdersSetStatusContext ctx) {
    String foId = extractId(ctx.getCqn());

    domainService.setStatus(foId, ctx.getNewStatusCode());

    ctx.setResult(freightOrderUIService.getFOById(foId));
  }

  @Before(event = DraftService.EVENT_DRAFT_EDIT, entity = cds.gen.freightorderuiservice.FreightOrders_.CDS_NAME)
  public void blockDraftEdit(DraftEditEventContext ctx) {
    throw new ServiceException(ErrorStatuses.METHOD_NOT_ALLOWED,
        "Standard edit is not supported. Use actions to modify Freight Order.");
  }

  private String extractId(CqnSelect cqn) {
    return (String) analyzer.analyze(cqn).targetKeys().get(FreightOrders.ID);
  }
}

package customer.tms_java.handlers;

import org.springframework.stereotype.Component;

import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.cds.CdsDeleteEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.freightorderuiservice.FreightOrderUIService_;
import cds.gen.freightorderuiservice.FreightOrders;
import cds.gen.freightorderuiservice.FreightOrders_;
import customer.tms_java.domain.FreightOrderDomainService;

@Component
@ServiceName(FreightOrderUIService_.CDS_NAME)
public class FreightOrderUIServiceValidationHandler implements EventHandler {

  private final FreightOrderDomainService domainService;
  private final CqnAnalyzer analyzer;

  public FreightOrderUIServiceValidationHandler(FreightOrderDomainService domainService, CdsModel model) {
    this.domainService = domainService;
    this.analyzer = CqnAnalyzer.create(model);
  }

  @Before(event = CqnService.EVENT_DELETE, entity = FreightOrders_.CDS_NAME)
  public void validateCanDelete(CdsDeleteEventContext ctx) {
    String foId = (String) analyzer.analyze(ctx.getCqn()).targetKeys().get(FreightOrders.ID);
    if (foId != null) {
      domainService.validateCanDelete(foId);
    }
  }
}

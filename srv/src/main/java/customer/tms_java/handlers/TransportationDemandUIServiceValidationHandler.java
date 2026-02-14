package customer.tms_java.handlers;

import org.springframework.stereotype.Component;

import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnDelete;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.cds.CdsDeleteEventContext;
import com.sap.cds.services.cds.CdsUpdateEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.draft.DraftEditEventContext;
import com.sap.cds.services.draft.DraftSaveEventContext;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.transportationdemanduiservice.TransportationDemandUIService_;
import cds.gen.transportationdemanduiservice.TransportationDemands;
import cds.gen.transportationdemanduiservice.TransportationDemands_;
import customer.tms_java.domain.TransportationDemandDomainService;

@Component
@ServiceName(TransportationDemandUIService_.CDS_NAME)
public class TransportationDemandUIServiceValidationHandler implements EventHandler {

  private final TransportationDemandDomainService domainService;
  private final CqnAnalyzer analyzer;

  public TransportationDemandUIServiceValidationHandler(TransportationDemandDomainService domainService,
      CdsModel model) {
    this.domainService = domainService;
    this.analyzer = CqnAnalyzer.create(model);
  }

  @Before(event = DraftService.EVENT_DRAFT_EDIT, entity = TransportationDemands_.CDS_NAME)
  public void beforeDraftEdit(DraftEditEventContext ctx, TransportationDemands td) {
    if (td != null && td.getId() != null) {
      domainService.validateCanModify(td.getId());
    }
  }

  @Before(event = DraftService.EVENT_DRAFT_SAVE, entity = TransportationDemands_.CDS_NAME)
  public void beforeDraftSave(DraftSaveEventContext ctx, TransportationDemands td) {
    if (td != null && td.getId() != null) {
      domainService.validateCanModify(td.getId());
    }
  }

  @Before(event = CqnService.EVENT_UPDATE, entity = TransportationDemands_.CDS_NAME)
  public void beforeUpdate(CdsUpdateEventContext ctx) {
    String tdId = extractId(ctx.getCqn());
    if (tdId != null) {
      domainService.validateCanModify(tdId);
    }
  }

  @Before(event = CqnService.EVENT_DELETE, entity = TransportationDemands_.CDS_NAME)
  public void beforeDelete(CdsDeleteEventContext ctx) {
    String tdId = extractId(ctx.getCqn());
    if (tdId != null) {
      domainService.validateCanModify(tdId);
    }
  }

  private String extractId(CqnUpdate cqn) {
    return (String) analyzer.analyze(cqn).targetKeys().get(TransportationDemands.ID);
  }

  private String extractId(CqnDelete cqn) {
    return (String) analyzer.analyze(cqn).targetKeys().get(TransportationDemands.ID);
  }
}

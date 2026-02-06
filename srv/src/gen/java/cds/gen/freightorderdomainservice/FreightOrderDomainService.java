package cds.gen.freightorderdomainservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.services.cds.ApplicationService;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.cds.RemoteService;
import com.sap.cds.services.draft.DraftService;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("cds-maven-plugin")
@CdsName(FreightOrderDomainService_.CDS_NAME)
public interface FreightOrderDomainService extends CqnService {
  @CdsName(AssignTDContext.CDS_NAME)
  FreightOrders assignTD(@CdsName(AssignTDContext.ID) String id,
      @CdsName(AssignTDContext.TD_DISPLAY_ID) String tdDisplayId);

  @CdsName(UnassignTDContext.CDS_NAME)
  FreightOrders unassignTD(@CdsName(UnassignTDContext.ID) String id,
      @CdsName(UnassignTDContext.TD_ID) String tdId);

  @CdsName(SetStatusContext.CDS_NAME)
  FreightOrders setStatus(@CdsName(SetStatusContext.ID) String id,
      @CdsName(SetStatusContext.NEW_STATUS_CODE) String newStatusCode);

  interface Application extends ApplicationService, FreightOrderDomainService {
  }

  interface Remote extends RemoteService, FreightOrderDomainService {
  }

  interface Draft extends DraftService, FreightOrderDomainService {
  }
}

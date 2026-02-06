package cds.gen.freightorderservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.services.cds.ApplicationService;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.cds.RemoteService;
import com.sap.cds.services.draft.DraftService;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("cds-maven-plugin")
@CdsName(FreightOrderService_.CDS_NAME)
public interface FreightOrderService extends CqnService {
  @CdsName(FreightOrdersUnassignTDContext.CDS_NAME)
  FreightOrders unassignTD(FreightOrders_ ref,
      @CdsName(FreightOrdersUnassignTDContext.TD_ID) String tdId);

  @CdsName(FreightOrdersAssignTDContext.CDS_NAME)
  FreightOrders assignTD(FreightOrders_ ref,
      @CdsName(FreightOrdersAssignTDContext.TD_DISPLAY_ID) String tdDisplayId);

  @CdsName(FreightOrdersSetStatusContext.CDS_NAME)
  FreightOrders setStatus(FreightOrders_ ref,
      @CdsName(FreightOrdersSetStatusContext.NEW_STATUS_CODE) String newStatusCode);

  interface Application extends ApplicationService, FreightOrderService {
  }

  interface Remote extends RemoteService, FreightOrderService {
  }

  interface Draft extends DraftService, FreightOrderService {
  }
}

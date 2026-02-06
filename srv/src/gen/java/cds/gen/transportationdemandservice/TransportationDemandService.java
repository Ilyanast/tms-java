package cds.gen.transportationdemandservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.services.cds.ApplicationService;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.cds.RemoteService;
import com.sap.cds.services.draft.DraftService;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("cds-maven-plugin")
@CdsName(TransportationDemandService_.CDS_NAME)
public interface TransportationDemandService extends CqnService {
  @CdsName(TransportationDemandsAssignContext.CDS_NAME)
  TransportationDemands assign(TransportationDemands_ ref,
      @CdsName(TransportationDemandsAssignContext.FREIGHT_ORDER_ID) String freightOrderId);

  @CdsName(TransportationDemandsUnassignContext.CDS_NAME)
  TransportationDemands unassign(TransportationDemands_ ref);

  interface Application extends ApplicationService, TransportationDemandService {
  }

  interface Remote extends RemoteService, TransportationDemandService {
  }

  interface Draft extends DraftService, TransportationDemandService {
  }
}

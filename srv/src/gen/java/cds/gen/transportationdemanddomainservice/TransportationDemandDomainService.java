package cds.gen.transportationdemanddomainservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.services.cds.ApplicationService;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.cds.RemoteService;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("cds-maven-plugin")
@CdsName(TransportationDemandDomainService_.CDS_NAME)
public interface TransportationDemandDomainService extends CqnService {
  @CdsName(AssignContext.CDS_NAME)
  TransportationDemands assign(@CdsName(AssignContext.ID) String id,
      @CdsName(AssignContext.FREIGHT_ORDER_ID) String freightOrderId);

  @CdsName(UnassignContext.CDS_NAME)
  TransportationDemands unassign(@CdsName(UnassignContext.ID) String id);

  interface Application extends ApplicationService, TransportationDemandDomainService {
  }

  interface Remote extends RemoteService, TransportationDemandDomainService {
  }
}

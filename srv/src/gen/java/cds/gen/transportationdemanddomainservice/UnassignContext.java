package cds.gen.transportationdemanddomainservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("unassign")
@Generated("cds-maven-plugin")
public interface UnassignContext extends EventContext {
  String ID = "ID";

  String CDS_NAME = "unassign";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  @Override
  TransportationDemandDomainService getService();

  void setResult(TransportationDemands result);

  TransportationDemands getResult();

  static UnassignContext create() {
    return EventContext.create(UnassignContext.class, null);
  }
}

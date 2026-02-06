package cds.gen.transportationdemanddomainservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("assign")
@Generated("cds-maven-plugin")
public interface AssignContext extends EventContext {
  String ID = "ID";

  String FREIGHT_ORDER_ID = "freightOrderId";

  String CDS_NAME = "assign";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  String getFreightOrderId();

  void setFreightOrderId(String freightOrderId);

  @Override
  TransportationDemandDomainService getService();

  void setResult(TransportationDemands result);

  TransportationDemands getResult();

  static AssignContext create() {
    return EventContext.create(AssignContext.class, null);
  }
}

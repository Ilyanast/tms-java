package cds.gen.transportationdemanduiservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("assign")
@Generated("cds-maven-plugin")
public interface TransportationDemandsAssignContext extends EventContext {
  String FREIGHT_ORDER_ID = "freightOrderId";

  String CDS_NAME = "assign";

  String getFreightOrderId();

  void setFreightOrderId(String freightOrderId);

  @Override
  TransportationDemandUIService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static TransportationDemandsAssignContext create() {
    return EventContext.create(TransportationDemandsAssignContext.class, "TransportationDemandUIService.TransportationDemands");
  }

  void setResult(TransportationDemands result);

  TransportationDemands getResult();

  static TransportationDemandsAssignContext create(String entityName) {
    return EventContext.create(TransportationDemandsAssignContext.class, entityName);
  }
}

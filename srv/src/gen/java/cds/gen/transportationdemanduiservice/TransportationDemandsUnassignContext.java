package cds.gen.transportationdemanduiservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("unassign")
@Generated("cds-maven-plugin")
public interface TransportationDemandsUnassignContext extends EventContext {
  String CDS_NAME = "unassign";

  @Override
  TransportationDemandUIService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static TransportationDemandsUnassignContext create() {
    return EventContext.create(TransportationDemandsUnassignContext.class, "TransportationDemandUIService.TransportationDemands");
  }

  void setResult(TransportationDemands result);

  TransportationDemands getResult();

  static TransportationDemandsUnassignContext create(String entityName) {
    return EventContext.create(TransportationDemandsUnassignContext.class, entityName);
  }
}

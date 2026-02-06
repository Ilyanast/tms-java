package cds.gen.transportationdemanduiservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("draftActivate")
@Generated("cds-maven-plugin")
public interface TransportationDemandsDraftActivateContext extends EventContext {
  String CDS_NAME = "draftActivate";

  @Override
  TransportationDemandUIService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static TransportationDemandsDraftActivateContext create() {
    return EventContext.create(TransportationDemandsDraftActivateContext.class, "TransportationDemandUIService.TransportationDemands");
  }

  void setResult(TransportationDemands result);

  TransportationDemands getResult();

  static TransportationDemandsDraftActivateContext create(String entityName) {
    return EventContext.create(TransportationDemandsDraftActivateContext.class, entityName);
  }
}

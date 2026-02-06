package cds.gen.freightorderservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("setStatus")
@Generated("cds-maven-plugin")
public interface FreightOrdersSetStatusContext extends EventContext {
  String NEW_STATUS_CODE = "newStatusCode";

  String CDS_NAME = "setStatus";

  String getNewStatusCode();

  void setNewStatusCode(String newStatusCode);

  @Override
  FreightOrderService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static FreightOrdersSetStatusContext create() {
    return EventContext.create(FreightOrdersSetStatusContext.class, "FreightOrderService.FreightOrders");
  }

  void setResult(FreightOrders result);

  FreightOrders getResult();

  static FreightOrdersSetStatusContext create(String entityName) {
    return EventContext.create(FreightOrdersSetStatusContext.class, entityName);
  }
}

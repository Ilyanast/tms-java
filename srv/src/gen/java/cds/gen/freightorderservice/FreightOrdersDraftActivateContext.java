package cds.gen.freightorderservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("draftActivate")
@Generated("cds-maven-plugin")
public interface FreightOrdersDraftActivateContext extends EventContext {
  String CDS_NAME = "draftActivate";

  @Override
  FreightOrderService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static FreightOrdersDraftActivateContext create() {
    return EventContext.create(FreightOrdersDraftActivateContext.class, "FreightOrderService.FreightOrders");
  }

  void setResult(FreightOrders result);

  FreightOrders getResult();

  static FreightOrdersDraftActivateContext create(String entityName) {
    return EventContext.create(FreightOrdersDraftActivateContext.class, entityName);
  }
}

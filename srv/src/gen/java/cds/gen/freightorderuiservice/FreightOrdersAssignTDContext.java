package cds.gen.freightorderuiservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("assignTD")
@Generated("cds-maven-plugin")
public interface FreightOrdersAssignTDContext extends EventContext {
  String TD_DISPLAY_ID = "tdDisplayId";

  String CDS_NAME = "assignTD";

  String getTdDisplayId();

  void setTdDisplayId(String tdDisplayId);

  @Override
  FreightOrderUIService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static FreightOrdersAssignTDContext create() {
    return EventContext.create(FreightOrdersAssignTDContext.class, "FreightOrderUIService.FreightOrders");
  }

  void setResult(FreightOrders result);

  FreightOrders getResult();

  static FreightOrdersAssignTDContext create(String entityName) {
    return EventContext.create(FreightOrdersAssignTDContext.class, entityName);
  }
}

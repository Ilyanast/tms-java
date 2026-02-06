package cds.gen.freightorderservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("unassignTD")
@Generated("cds-maven-plugin")
public interface FreightOrdersUnassignTDContext extends EventContext {
  String TD_ID = "tdId";

  String CDS_NAME = "unassignTD";

  String getTdId();

  void setTdId(String tdId);

  @Override
  FreightOrderService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static FreightOrdersUnassignTDContext create() {
    return EventContext.create(FreightOrdersUnassignTDContext.class, "FreightOrderService.FreightOrders");
  }

  void setResult(FreightOrders result);

  FreightOrders getResult();

  static FreightOrdersUnassignTDContext create(String entityName) {
    return EventContext.create(FreightOrdersUnassignTDContext.class, entityName);
  }
}

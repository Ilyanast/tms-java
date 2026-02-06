package cds.gen.freightorderservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("moveStop")
@Generated("cds-maven-plugin")
public interface FreightOrderStopsMoveStopContext extends EventContext {
  String NEW_SEQUENCE = "newSequence";

  String CDS_NAME = "moveStop";

  Integer getNewSequence();

  void setNewSequence(Integer newSequence);

  @Override
  FreightOrderService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static FreightOrderStopsMoveStopContext create() {
    return EventContext.create(FreightOrderStopsMoveStopContext.class, "FreightOrderService.FreightOrderStops");
  }

  void setResult(FreightOrderStops result);

  FreightOrderStops getResult();

  static FreightOrderStopsMoveStopContext create(String entityName) {
    return EventContext.create(FreightOrderStopsMoveStopContext.class, entityName);
  }
}

package cds.gen.freightorderservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("draftPrepare")
@Generated("cds-maven-plugin")
public interface FreightOrdersDraftPrepareContext extends EventContext {
  String SIDE_EFFECTS_QUALIFIER = "SideEffectsQualifier";

  String CDS_NAME = "draftPrepare";

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  String getSideEffectsQualifier();

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  void setSideEffectsQualifier(String sideEffectsQualifier);

  @Override
  FreightOrderService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static FreightOrdersDraftPrepareContext create() {
    return EventContext.create(FreightOrdersDraftPrepareContext.class, "FreightOrderService.FreightOrders");
  }

  void setResult(FreightOrders result);

  FreightOrders getResult();

  static FreightOrdersDraftPrepareContext create(String entityName) {
    return EventContext.create(FreightOrdersDraftPrepareContext.class, entityName);
  }
}

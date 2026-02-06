package cds.gen.freightorderuiservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("draftPrepare")
@Generated("cds-maven-plugin")
public interface FreightOrderItemsDraftPrepareContext extends EventContext {
  String SIDE_EFFECTS_QUALIFIER = "SideEffectsQualifier";

  String CDS_NAME = "draftPrepare";

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  String getSideEffectsQualifier();

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  void setSideEffectsQualifier(String sideEffectsQualifier);

  @Override
  FreightOrderUIService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static FreightOrderItemsDraftPrepareContext create() {
    return EventContext.create(FreightOrderItemsDraftPrepareContext.class, "FreightOrderUIService.FreightOrderItems");
  }

  void setResult(FreightOrderItems result);

  FreightOrderItems getResult();

  static FreightOrderItemsDraftPrepareContext create(String entityName) {
    return EventContext.create(FreightOrderItemsDraftPrepareContext.class, entityName);
  }
}

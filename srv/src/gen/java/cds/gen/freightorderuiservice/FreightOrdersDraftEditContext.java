package cds.gen.freightorderuiservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("draftEdit")
@Generated("cds-maven-plugin")
public interface FreightOrdersDraftEditContext extends EventContext {
  String PRESERVE_CHANGES = "PreserveChanges";

  String CDS_NAME = "draftEdit";

  @CdsName(PRESERVE_CHANGES)
  Boolean getPreserveChanges();

  @CdsName(PRESERVE_CHANGES)
  void setPreserveChanges(Boolean preserveChanges);

  @Override
  FreightOrderUIService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static FreightOrdersDraftEditContext create() {
    return EventContext.create(FreightOrdersDraftEditContext.class, "FreightOrderUIService.FreightOrders");
  }

  void setResult(FreightOrders result);

  FreightOrders getResult();

  static FreightOrdersDraftEditContext create(String entityName) {
    return EventContext.create(FreightOrdersDraftEditContext.class, entityName);
  }
}

package cds.gen.transportationdemandservice;

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
public interface TransportationDemandsDraftEditContext extends EventContext {
  String PRESERVE_CHANGES = "PreserveChanges";

  String CDS_NAME = "draftEdit";

  @CdsName(PRESERVE_CHANGES)
  Boolean getPreserveChanges();

  @CdsName(PRESERVE_CHANGES)
  void setPreserveChanges(Boolean preserveChanges);

  @Override
  TransportationDemandService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static TransportationDemandsDraftEditContext create() {
    return EventContext.create(TransportationDemandsDraftEditContext.class, "TransportationDemandService.TransportationDemands");
  }

  void setResult(TransportationDemands result);

  TransportationDemands getResult();

  static TransportationDemandsDraftEditContext create(String entityName) {
    return EventContext.create(TransportationDemandsDraftEditContext.class, entityName);
  }
}

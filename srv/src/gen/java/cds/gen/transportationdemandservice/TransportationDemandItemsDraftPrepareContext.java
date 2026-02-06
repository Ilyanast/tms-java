package cds.gen.transportationdemandservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("draftPrepare")
@Generated("cds-maven-plugin")
public interface TransportationDemandItemsDraftPrepareContext extends EventContext {
  String SIDE_EFFECTS_QUALIFIER = "SideEffectsQualifier";

  String CDS_NAME = "draftPrepare";

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  String getSideEffectsQualifier();

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  void setSideEffectsQualifier(String sideEffectsQualifier);

  @Override
  TransportationDemandService getService();

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static TransportationDemandItemsDraftPrepareContext create() {
    return EventContext.create(TransportationDemandItemsDraftPrepareContext.class, "TransportationDemandService.TransportationDemandItems");
  }

  void setResult(TransportationDemandItems result);

  TransportationDemandItems getResult();

  static TransportationDemandItemsDraftPrepareContext create(String entityName) {
    return EventContext.create(TransportationDemandItemsDraftPrepareContext.class, entityName);
  }
}

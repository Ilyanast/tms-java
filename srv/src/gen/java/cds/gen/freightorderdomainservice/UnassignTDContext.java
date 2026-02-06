package cds.gen.freightorderdomainservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("unassignTD")
@Generated("cds-maven-plugin")
public interface UnassignTDContext extends EventContext {
  String ID = "ID";

  String TD_ID = "tdId";

  String CDS_NAME = "unassignTD";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  String getTdId();

  void setTdId(String tdId);

  @Override
  FreightOrderDomainService getService();

  void setResult(FreightOrders result);

  FreightOrders getResult();

  static UnassignTDContext create() {
    return EventContext.create(UnassignTDContext.class, null);
  }
}

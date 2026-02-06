package cds.gen.freightorderdomainservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("setStatus")
@Generated("cds-maven-plugin")
public interface SetStatusContext extends EventContext {
  String ID = "ID";

  String NEW_STATUS_CODE = "newStatusCode";

  String CDS_NAME = "setStatus";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  String getNewStatusCode();

  void setNewStatusCode(String newStatusCode);

  @Override
  FreightOrderDomainService getService();

  void setResult(FreightOrders result);

  FreightOrders getResult();

  static SetStatusContext create() {
    return EventContext.create(SetStatusContext.class, null);
  }
}

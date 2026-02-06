package cds.gen.freightorderdomainservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("assignTD")
@Generated("cds-maven-plugin")
public interface AssignTDContext extends EventContext {
  String ID = "ID";

  String TD_DISPLAY_ID = "tdDisplayId";

  String CDS_NAME = "assignTD";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  String getTdDisplayId();

  void setTdDisplayId(String tdDisplayId);

  @Override
  FreightOrderDomainService getService();

  void setResult(FreightOrders result);

  FreightOrders getResult();

  static AssignTDContext create() {
    return EventContext.create(AssignTDContext.class, null);
  }
}

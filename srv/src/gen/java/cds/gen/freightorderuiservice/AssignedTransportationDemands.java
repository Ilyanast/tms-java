package cds.gen.freightorderuiservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderUIService.AssignedTransportationDemands")
@Generated("cds-maven-plugin")
public interface AssignedTransportationDemands extends CdsData {
  String ID = "ID";

  String DISPLAY_ID = "displayId";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  String getDisplayId();

  void setDisplayId(String displayId);

  AssignedTransportationDemands_ ref();

  static AssignedTransportationDemands create() {
    return Struct.create(AssignedTransportationDemands.class);
  }

  static AssignedTransportationDemands of(Map<String, Object> map) {
    return Struct.access(map).as(AssignedTransportationDemands.class);
  }

  static AssignedTransportationDemands create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(AssignedTransportationDemands.class);
  }
}

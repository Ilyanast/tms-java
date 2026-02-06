package cds.gen.transportationdemanduiservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandUIService.FreightOrders")
@Generated("cds-maven-plugin")
public interface FreightOrders extends CdsData {
  String ID = "ID";

  String DISPLAY_ID = "displayId";

  String STATUS_NAME = "statusName";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  String getDisplayId();

  void setDisplayId(String displayId);

  String getStatusName();

  void setStatusName(String statusName);

  FreightOrders_ ref();

  static FreightOrders create() {
    return Struct.create(FreightOrders.class);
  }

  static FreightOrders of(Map<String, Object> map) {
    return Struct.access(map).as(FreightOrders.class);
  }

  static FreightOrders create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(FreightOrders.class);
  }
}

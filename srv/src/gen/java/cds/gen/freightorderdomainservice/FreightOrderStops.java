package cds.gen.freightorderdomainservice;

import cds.gen.tms.Location;
import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderDomainService.FreightOrderStops")
@Generated("cds-maven-plugin")
public interface FreightOrderStops extends CdsData {
  String ID = "ID";

  String FREIGHT_ORDER = "freightOrder";

  String FREIGHT_ORDER_ID = "freightOrder_ID";

  String LOCATION = "location";

  String LOCATION_ID = "location_ID";

  String SEQUENCE = "sequence";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  FreightOrders getFreightOrder();

  void setFreightOrder(Map<String, ?> freightOrder);

  @CdsName(FREIGHT_ORDER_ID)
  String getFreightOrderId();

  @CdsName(FREIGHT_ORDER_ID)
  void setFreightOrderId(String freightOrderId);

  Location getLocation();

  void setLocation(Map<String, ?> location);

  @CdsName(LOCATION_ID)
  String getLocationId();

  @CdsName(LOCATION_ID)
  void setLocationId(String locationId);

  Integer getSequence();

  void setSequence(Integer sequence);

  FreightOrderStops_ ref();

  static FreightOrderStops create() {
    return Struct.create(FreightOrderStops.class);
  }

  static FreightOrderStops of(Map<String, Object> map) {
    return Struct.access(map).as(FreightOrderStops.class);
  }

  static FreightOrderStops create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(FreightOrderStops.class);
  }
}

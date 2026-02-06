package cds.gen.freightorderuiservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderUIService.UnassignedTransportationDemands")
@Generated("cds-maven-plugin")
public interface UnassignedTransportationDemands extends CdsData {
  String ID = "ID";

  String DISPLAY_ID = "displayId";

  String FROM_LOCATION = "fromLocation";

  String FROM_LOCATION_ID = "fromLocation_ID";

  String TO_LOCATION = "toLocation";

  String TO_LOCATION_ID = "toLocation_ID";

  String DELIVERY_DATE_TIME = "deliveryDateTime";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  String getDisplayId();

  void setDisplayId(String displayId);

  Locations getFromLocation();

  void setFromLocation(Map<String, ?> fromLocation);

  @CdsName(FROM_LOCATION_ID)
  String getFromLocationId();

  @CdsName(FROM_LOCATION_ID)
  void setFromLocationId(String fromLocationId);

  Locations getToLocation();

  void setToLocation(Map<String, ?> toLocation);

  @CdsName(TO_LOCATION_ID)
  String getToLocationId();

  @CdsName(TO_LOCATION_ID)
  void setToLocationId(String toLocationId);

  Instant getDeliveryDateTime();

  void setDeliveryDateTime(Instant deliveryDateTime);

  UnassignedTransportationDemands_ ref();

  static UnassignedTransportationDemands create() {
    return Struct.create(UnassignedTransportationDemands.class);
  }

  static UnassignedTransportationDemands of(Map<String, Object> map) {
    return Struct.access(map).as(UnassignedTransportationDemands.class);
  }

  static UnassignedTransportationDemands create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(UnassignedTransportationDemands.class);
  }
}

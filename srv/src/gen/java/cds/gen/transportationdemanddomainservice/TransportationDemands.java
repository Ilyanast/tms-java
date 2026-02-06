package cds.gen.transportationdemanddomainservice;

import cds.gen.tms.FreightOrder;
import cds.gen.tms.Location;
import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandDomainService.TransportationDemands")
@Generated("cds-maven-plugin")
public interface TransportationDemands extends CdsData {
  String ID = "ID";

  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String MODIFIED_AT = "modifiedAt";

  String MODIFIED_BY = "modifiedBy";

  String DISPLAY_ID = "displayId";

  String FROM_LOCATION = "fromLocation";

  String FROM_LOCATION_ID = "fromLocation_ID";

  String TO_LOCATION = "toLocation";

  String TO_LOCATION_ID = "toLocation_ID";

  String DELIVERY_DATE_TIME = "deliveryDateTime";

  String FREIGHT_ORDER = "freightOrder";

  String FREIGHT_ORDER_ID = "freightOrder_ID";

  String ITEMS = "items";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  Instant getCreatedAt();

  void setCreatedAt(Instant createdAt);

  String getCreatedBy();

  void setCreatedBy(String createdBy);

  Instant getModifiedAt();

  void setModifiedAt(Instant modifiedAt);

  String getModifiedBy();

  void setModifiedBy(String modifiedBy);

  String getDisplayId();

  void setDisplayId(String displayId);

  Location getFromLocation();

  void setFromLocation(Map<String, ?> fromLocation);

  @CdsName(FROM_LOCATION_ID)
  String getFromLocationId();

  @CdsName(FROM_LOCATION_ID)
  void setFromLocationId(String fromLocationId);

  Location getToLocation();

  void setToLocation(Map<String, ?> toLocation);

  @CdsName(TO_LOCATION_ID)
  String getToLocationId();

  @CdsName(TO_LOCATION_ID)
  void setToLocationId(String toLocationId);

  Instant getDeliveryDateTime();

  void setDeliveryDateTime(Instant deliveryDateTime);

  FreightOrder getFreightOrder();

  void setFreightOrder(Map<String, ?> freightOrder);

  @CdsName(FREIGHT_ORDER_ID)
  String getFreightOrderId();

  @CdsName(FREIGHT_ORDER_ID)
  void setFreightOrderId(String freightOrderId);

  List<TransportationDemandItems> getItems();

  void setItems(List<? extends Map<String, ?>> items);

  TransportationDemands_ ref();

  static TransportationDemands create() {
    return Struct.create(TransportationDemands.class);
  }

  static TransportationDemands of(Map<String, Object> map) {
    return Struct.access(map).as(TransportationDemands.class);
  }

  static TransportationDemands create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(TransportationDemands.class);
  }
}

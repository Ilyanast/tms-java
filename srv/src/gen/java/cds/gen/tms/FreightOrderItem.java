package cds.gen.tms;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("tms.FreightOrderItem")
@Generated("cds-maven-plugin")
public interface FreightOrderItem extends CdsData {
  String ID = "ID";

  String DISPLAY_ID = "displayId";

  String FREIGHT_ORDER = "freightOrder";

  String FREIGHT_ORDER_ID = "freightOrder_ID";

  String PRODUCT_NAME = "productName";

  String QUANTITY = "quantity";

  String TRANSPORTATION_DEMAND_ITEM = "transportationDemandItem";

  String TRANSPORTATION_DEMAND_ITEM_ID = "transportationDemandItem_ID";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  String getDisplayId();

  void setDisplayId(String displayId);

  FreightOrder getFreightOrder();

  void setFreightOrder(Map<String, ?> freightOrder);

  @CdsName(FREIGHT_ORDER_ID)
  String getFreightOrderId();

  @CdsName(FREIGHT_ORDER_ID)
  void setFreightOrderId(String freightOrderId);

  String getProductName();

  void setProductName(String productName);

  Integer getQuantity();

  void setQuantity(Integer quantity);

  TransportationDemandItem getTransportationDemandItem();

  void setTransportationDemandItem(Map<String, ?> transportationDemandItem);

  @CdsName(TRANSPORTATION_DEMAND_ITEM_ID)
  String getTransportationDemandItemId();

  @CdsName(TRANSPORTATION_DEMAND_ITEM_ID)
  void setTransportationDemandItemId(String transportationDemandItemId);

  FreightOrderItem_ ref();

  static FreightOrderItem create() {
    return Struct.create(FreightOrderItem.class);
  }

  static FreightOrderItem of(Map<String, Object> map) {
    return Struct.access(map).as(FreightOrderItem.class);
  }

  static FreightOrderItem create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(FreightOrderItem.class);
  }
}

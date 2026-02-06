package cds.gen.freightorderdomainservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderDomainService.FreightOrderItems")
@Generated("cds-maven-plugin")
public interface FreightOrderItems extends CdsData {
  String ID = "ID";

  String DISPLAY_ID = "displayId";

  String FREIGHT_ORDER = "freightOrder";

  String FREIGHT_ORDER_ID = "freightOrder_ID";

  String PRODUCT_NAME = "productName";

  String QUANTITY = "quantity";

  String TRANSPORTATION_DEMAND_ITEM = "transportationDemandItem";

  String TRANSPORTATION_DEMAND_ITEM_ID = "transportationDemandItem_ID";

  String TD_DISPLAY_ID = "tdDisplayId";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  String getDisplayId();

  void setDisplayId(String displayId);

  FreightOrders getFreightOrder();

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

  String getTdDisplayId();

  void setTdDisplayId(String tdDisplayId);

  FreightOrderItems_ ref();

  static FreightOrderItems create() {
    return Struct.create(FreightOrderItems.class);
  }

  static FreightOrderItems of(Map<String, Object> map) {
    return Struct.access(map).as(FreightOrderItems.class);
  }

  static FreightOrderItems create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(FreightOrderItems.class);
  }
}

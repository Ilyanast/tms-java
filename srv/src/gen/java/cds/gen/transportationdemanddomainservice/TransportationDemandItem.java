package cds.gen.transportationdemanddomainservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandDomainService.TransportationDemandItem")
@Generated("cds-maven-plugin")
public interface TransportationDemandItem extends CdsData {
  String ID = "ID";

  String DISPLAY_ID = "displayId";

  String TRANSPORTATION_DEMAND = "transportationDemand";

  String TRANSPORTATION_DEMAND_ID = "transportationDemand_ID";

  String PRODUCT_NAME = "productName";

  String QUANTITY = "quantity";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  String getDisplayId();

  void setDisplayId(String displayId);

  TransportationDemands getTransportationDemand();

  void setTransportationDemand(Map<String, ?> transportationDemand);

  @CdsName(TRANSPORTATION_DEMAND_ID)
  String getTransportationDemandId();

  @CdsName(TRANSPORTATION_DEMAND_ID)
  void setTransportationDemandId(String transportationDemandId);

  String getProductName();

  void setProductName(String productName);

  Integer getQuantity();

  void setQuantity(Integer quantity);

  TransportationDemandItem_ ref();

  static TransportationDemandItem create() {
    return Struct.create(TransportationDemandItem.class);
  }

  static TransportationDemandItem of(Map<String, Object> map) {
    return Struct.access(map).as(TransportationDemandItem.class);
  }

  static TransportationDemandItem create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(TransportationDemandItem.class);
  }
}

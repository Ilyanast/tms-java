package cds.gen.freightorderdomainservice;

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

@CdsName("FreightOrderDomainService.FreightOrders")
@Generated("cds-maven-plugin")
public interface FreightOrders extends CdsData {
  String ID = "ID";

  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String MODIFIED_AT = "modifiedAt";

  String MODIFIED_BY = "modifiedBy";

  String DISPLAY_ID = "displayId";

  String STATUS = "status";

  String STATUS_CODE = "status_code";

  String ITEMS = "items";

  String STOPS = "stops";

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

  FreightOrderStatus getStatus();

  void setStatus(Map<String, ?> status);

  @CdsName(STATUS_CODE)
  String getStatusCode();

  @CdsName(STATUS_CODE)
  void setStatusCode(String statusCode);

  List<FreightOrderItems> getItems();

  void setItems(List<? extends Map<String, ?>> items);

  List<FreightOrderStops> getStops();

  void setStops(List<? extends Map<String, ?>> stops);

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

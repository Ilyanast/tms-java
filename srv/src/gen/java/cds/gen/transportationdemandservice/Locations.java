package cds.gen.transportationdemandservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandService.Locations")
@Generated("cds-maven-plugin")
public interface Locations extends CdsData {
  String ID = "ID";

  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String MODIFIED_AT = "modifiedAt";

  String MODIFIED_BY = "modifiedBy";

  String DISPLAY_ID = "displayId";

  String NAME = "name";

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

  String getName();

  void setName(String name);

  Locations_ ref();

  static Locations create() {
    return Struct.create(Locations.class);
  }

  static Locations of(Map<String, Object> map) {
    return Struct.access(map).as(Locations.class);
  }

  static Locations create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(Locations.class);
  }
}

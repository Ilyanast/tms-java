package cds.gen.transportationdemanduiservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandUIService.FreightOrderStatus")
@Generated("cds-maven-plugin")
public interface FreightOrderStatus extends CdsData {
  String NAME = "name";

  String DESCR = "descr";

  String CODE = "code";

  String TEXTS = "texts";

  String LOCALIZED = "localized";

  String getName();

  void setName(String name);

  String getDescr();

  void setDescr(String descr);

  String getCode();

  void setCode(String code);

  List<FreightOrderStatusTexts> getTexts();

  void setTexts(List<? extends Map<String, ?>> texts);

  FreightOrderStatusTexts getLocalized();

  void setLocalized(Map<String, ?> localized);

  FreightOrderStatus_ ref();

  static FreightOrderStatus create() {
    return Struct.create(FreightOrderStatus.class);
  }

  static FreightOrderStatus of(Map<String, Object> map) {
    return Struct.access(map).as(FreightOrderStatus.class);
  }

  static FreightOrderStatus create(String code) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(CODE, code);
    return Struct.access(keys).as(FreightOrderStatus.class);
  }
}

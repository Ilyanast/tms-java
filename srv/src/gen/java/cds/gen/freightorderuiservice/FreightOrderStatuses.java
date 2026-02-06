package cds.gen.freightorderuiservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderUIService.FreightOrderStatuses")
@Generated("cds-maven-plugin")
public interface FreightOrderStatuses extends CdsData {
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

  List<FreightOrderStatusesTexts> getTexts();

  void setTexts(List<? extends Map<String, ?>> texts);

  FreightOrderStatusesTexts getLocalized();

  void setLocalized(Map<String, ?> localized);

  FreightOrderStatuses_ ref();

  static FreightOrderStatuses create() {
    return Struct.create(FreightOrderStatuses.class);
  }

  static FreightOrderStatuses of(Map<String, Object> map) {
    return Struct.access(map).as(FreightOrderStatuses.class);
  }

  static FreightOrderStatuses create(String code) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(CODE, code);
    return Struct.access(keys).as(FreightOrderStatuses.class);
  }
}

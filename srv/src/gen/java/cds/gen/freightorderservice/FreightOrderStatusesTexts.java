package cds.gen.freightorderservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderService.FreightOrderStatuses.texts")
@Generated("cds-maven-plugin")
public interface FreightOrderStatusesTexts extends CdsData {
  String LOCALE = "locale";

  String NAME = "name";

  String DESCR = "descr";

  String CODE = "code";

  String getLocale();

  void setLocale(String locale);

  String getName();

  void setName(String name);

  String getDescr();

  void setDescr(String descr);

  String getCode();

  void setCode(String code);

  FreightOrderStatusesTexts_ ref();

  static FreightOrderStatusesTexts create() {
    return Struct.create(FreightOrderStatusesTexts.class);
  }

  static FreightOrderStatusesTexts of(Map<String, Object> map) {
    return Struct.access(map).as(FreightOrderStatusesTexts.class);
  }
}

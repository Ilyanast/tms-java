package cds.gen.sap.common;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.common.Timezones.texts")
@Generated("cds-maven-plugin")
public interface TimezonesTexts extends CdsData {
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

  TimezonesTexts_ ref();

  static TimezonesTexts create() {
    return Struct.create(TimezonesTexts.class);
  }

  static TimezonesTexts of(Map<String, Object> map) {
    return Struct.access(map).as(TimezonesTexts.class);
  }
}

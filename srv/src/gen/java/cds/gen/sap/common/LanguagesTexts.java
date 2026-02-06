package cds.gen.sap.common;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.common.Languages.texts")
@Generated("cds-maven-plugin")
public interface LanguagesTexts extends CdsData {
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

  LanguagesTexts_ ref();

  static LanguagesTexts create() {
    return Struct.create(LanguagesTexts.class);
  }

  static LanguagesTexts of(Map<String, Object> map) {
    return Struct.access(map).as(LanguagesTexts.class);
  }
}

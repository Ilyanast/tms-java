package cds.gen.sap.common;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.common.TextsAspect")
@Generated("cds-maven-plugin")
public interface TextsAspect extends CdsData {
  String LOCALE = "locale";

  String getLocale();

  void setLocale(String locale);

  static TextsAspect create() {
    return Struct.create(TextsAspect.class);
  }

  static TextsAspect of(Map<String, Object> map) {
    return Struct.access(map).as(TextsAspect.class);
  }
}

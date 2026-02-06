package cds.gen.sap.common;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("sap.common.Timezones.texts")
@Generated("cds-maven-plugin")
public interface TimezonesTexts_ extends LinkedStructuredType<TimezonesTexts, TimezonesTexts_> {
  String CDS_NAME = "sap.common.Timezones.texts";

  ElementRef<String> locale();

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();
}

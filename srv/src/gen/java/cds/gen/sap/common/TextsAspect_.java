package cds.gen.sap.common;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("sap.common.TextsAspect")
@Generated("cds-maven-plugin")
public interface TextsAspect_ extends LinkedStructuredType<TextsAspect, TextsAspect_> {
  String CDS_NAME = "sap.common.TextsAspect";

  ElementRef<String> locale();
}

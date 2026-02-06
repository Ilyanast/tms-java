package cds.gen.freightorderuiservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderUIService.FreightOrderStatuses.texts")
@Generated("cds-maven-plugin")
public interface FreightOrderStatusesTexts_ extends LinkedStructuredType<FreightOrderStatusesTexts, FreightOrderStatusesTexts_> {
  String CDS_NAME = "FreightOrderUIService.FreightOrderStatuses.texts";

  ElementRef<String> locale();

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();
}

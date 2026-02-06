package cds.gen.transportationdemanduiservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandUIService.FreightOrderStatus.texts")
@Generated("cds-maven-plugin")
public interface FreightOrderStatusTexts_ extends LinkedStructuredType<FreightOrderStatusTexts, FreightOrderStatusTexts_> {
  String CDS_NAME = "TransportationDemandUIService.FreightOrderStatus.texts";

  ElementRef<String> locale();

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();
}

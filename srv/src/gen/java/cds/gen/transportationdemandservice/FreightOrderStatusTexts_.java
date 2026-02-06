package cds.gen.transportationdemandservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandService.FreightOrderStatus.texts")
@Generated("cds-maven-plugin")
public interface FreightOrderStatusTexts_ extends LinkedStructuredType<FreightOrderStatusTexts, FreightOrderStatusTexts_> {
  String CDS_NAME = "TransportationDemandService.FreightOrderStatus.texts";

  ElementRef<String> locale();

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();
}

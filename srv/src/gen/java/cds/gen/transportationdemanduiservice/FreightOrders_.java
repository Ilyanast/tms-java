package cds.gen.transportationdemanduiservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandUIService.FreightOrders")
@Generated("cds-maven-plugin")
public interface FreightOrders_ extends LinkedStructuredType<FreightOrders, FreightOrders_> {
  String ID = "ID";

  String CDS_NAME = "TransportationDemandUIService.FreightOrders";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<String> displayId();

  ElementRef<String> statusName();
}

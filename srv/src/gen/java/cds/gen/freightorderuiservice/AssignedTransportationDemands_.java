package cds.gen.freightorderuiservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderUIService.AssignedTransportationDemands")
@Generated("cds-maven-plugin")
public interface AssignedTransportationDemands_ extends LinkedStructuredType<AssignedTransportationDemands, AssignedTransportationDemands_> {
  String ID = "ID";

  String CDS_NAME = "FreightOrderUIService.AssignedTransportationDemands";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<String> displayId();
}

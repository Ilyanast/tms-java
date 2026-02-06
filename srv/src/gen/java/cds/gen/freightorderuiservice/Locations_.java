package cds.gen.freightorderuiservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import java.time.Instant;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderUIService.Locations")
@Generated("cds-maven-plugin")
public interface Locations_ extends LinkedStructuredType<Locations, Locations_> {
  String ID = "ID";

  String CDS_NAME = "FreightOrderUIService.Locations";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  ElementRef<Instant> modifiedAt();

  ElementRef<String> modifiedBy();

  ElementRef<String> displayId();

  ElementRef<String> name();
}

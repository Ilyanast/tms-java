package cds.gen.tms;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import java.time.Instant;
import javax.annotation.processing.Generated;

@CdsName("tms.Location")
@Generated("cds-maven-plugin")
public interface Location_ extends LinkedStructuredType<Location, Location_> {
  String ID = "ID";

  String CDS_NAME = "tms.Location";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  ElementRef<Instant> modifiedAt();

  ElementRef<String> modifiedBy();

  ElementRef<String> displayId();

  ElementRef<String> name();
}

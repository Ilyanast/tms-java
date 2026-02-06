package cds.gen;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import java.time.Instant;
import javax.annotation.processing.Generated;

/**
 * Aspect for entities with temporal data
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-temporal
 */
@CdsName("temporal")
@Generated("cds-maven-plugin")
public interface Temporal_ extends LinkedStructuredType<Temporal, Temporal_> {
  String CDS_NAME = "temporal";

  ElementRef<Instant> validFrom();

  ElementRef<Instant> validTo();
}

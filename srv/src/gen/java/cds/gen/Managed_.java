package cds.gen;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import java.time.Instant;
import javax.annotation.processing.Generated;

/**
 * Aspect to capture changes by user and name
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-managed
 */
@CdsName("managed")
@Generated("cds-maven-plugin")
public interface Managed_ extends LinkedStructuredType<Managed, Managed_> {
  String CDS_NAME = "managed";

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  ElementRef<Instant> modifiedAt();

  ElementRef<String> modifiedBy();
}

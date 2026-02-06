package cds.gen;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

/**
 * Aspect for entities with canonical universal IDs
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-cuid
 */
@CdsName("cuid")
@Generated("cds-maven-plugin")
public interface Cuid_ extends LinkedStructuredType<Cuid, Cuid_> {
  String ID = "ID";

  String CDS_NAME = "cuid";

  @CdsName(ID)
  ElementRef<String> ID();
}

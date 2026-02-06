package cds.gen.sap.common;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

/**
 * Aspect for a code list with name and description
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-codelist
 */
@CdsName("sap.common.CodeList")
@Generated("cds-maven-plugin")
public interface CodeList_ extends LinkedStructuredType<CodeList, CodeList_> {
  String CDS_NAME = "sap.common.CodeList";

  ElementRef<String> name();

  ElementRef<String> descr();
}

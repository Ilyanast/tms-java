package cds.gen.sap.common;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

/**
 * Code list for countries
 *
 * See https://cap.cloud.sap/docs/cds/common#entity-countries
 */
@CdsName("sap.common.Countries")
@Generated("cds-maven-plugin")
public interface Countries_ extends LinkedStructuredType<Countries, Countries_> {
  String CDS_NAME = "sap.common.Countries";

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();

  CountriesTexts_ texts();

  CountriesTexts_ texts(Function<CountriesTexts_, CqnPredicate> filter);

  CountriesTexts_ localized();

  CountriesTexts_ localized(Function<CountriesTexts_, CqnPredicate> filter);
}

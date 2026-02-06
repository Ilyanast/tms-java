package cds.gen.sap.common;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Short;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

/**
 * Code list for currencies
 *
 * See https://cap.cloud.sap/docs/cds/common#entity-currencies
 */
@CdsName("sap.common.Currencies")
@Generated("cds-maven-plugin")
public interface Currencies_ extends LinkedStructuredType<Currencies, Currencies_> {
  String CDS_NAME = "sap.common.Currencies";

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();

  ElementRef<String> symbol();

  ElementRef<Short> minorUnit();

  CurrenciesTexts_ texts();

  CurrenciesTexts_ texts(Function<CurrenciesTexts_, CqnPredicate> filter);

  CurrenciesTexts_ localized();

  CurrenciesTexts_ localized(Function<CurrenciesTexts_, CqnPredicate> filter);
}

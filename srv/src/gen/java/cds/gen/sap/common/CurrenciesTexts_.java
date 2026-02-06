package cds.gen.sap.common;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("sap.common.Currencies.texts")
@Generated("cds-maven-plugin")
public interface CurrenciesTexts_ extends LinkedStructuredType<CurrenciesTexts, CurrenciesTexts_> {
  String CDS_NAME = "sap.common.Currencies.texts";

  ElementRef<String> locale();

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();
}

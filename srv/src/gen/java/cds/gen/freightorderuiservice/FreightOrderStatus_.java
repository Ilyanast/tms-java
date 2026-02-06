package cds.gen.freightorderuiservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderUIService.FreightOrderStatus")
@Generated("cds-maven-plugin")
public interface FreightOrderStatus_ extends LinkedStructuredType<FreightOrderStatus, FreightOrderStatus_> {
  String CDS_NAME = "FreightOrderUIService.FreightOrderStatus";

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();

  FreightOrderStatusTexts_ texts();

  FreightOrderStatusTexts_ texts(Function<FreightOrderStatusTexts_, CqnPredicate> filter);

  FreightOrderStatusTexts_ localized();

  FreightOrderStatusTexts_ localized(Function<FreightOrderStatusTexts_, CqnPredicate> filter);
}

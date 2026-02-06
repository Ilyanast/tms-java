package cds.gen.freightorderservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderService.FreightOrderStatuses")
@Generated("cds-maven-plugin")
public interface FreightOrderStatuses_ extends LinkedStructuredType<FreightOrderStatuses, FreightOrderStatuses_> {
  String CDS_NAME = "FreightOrderService.FreightOrderStatuses";

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();

  FreightOrderStatusesTexts_ texts();

  FreightOrderStatusesTexts_ texts(Function<FreightOrderStatusesTexts_, CqnPredicate> filter);

  FreightOrderStatusesTexts_ localized();

  FreightOrderStatusesTexts_ localized(Function<FreightOrderStatusesTexts_, CqnPredicate> filter);
}

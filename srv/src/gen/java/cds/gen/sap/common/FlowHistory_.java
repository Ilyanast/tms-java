package cds.gen.sap.common;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.time.Instant;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("sap.common.FlowHistory")
@Generated("cds-maven-plugin")
public interface FlowHistory_ extends LinkedStructuredType<FlowHistory, FlowHistory_> {
  String CDS_NAME = "sap.common.FlowHistory";

  Transitions_ transitions_();

  Transitions_ transitions_(Function<Transitions_, CqnPredicate> filter);

  @CdsName("sap.common.FlowHistory.transitions_")
  interface Transitions_ extends LinkedStructuredType<FlowHistory.Transitions, Transitions_> {
    String CDS_NAME = "sap.common.FlowHistory.transitions_";

    ElementRef<Instant> timestamp();

    ElementRef<String> user();

    ElementRef<String> status();

    ElementRef<String> comment();
  }
}

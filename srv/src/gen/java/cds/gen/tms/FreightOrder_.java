package cds.gen.tms;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.time.Instant;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("tms.FreightOrder")
@Generated("cds-maven-plugin")
public interface FreightOrder_ extends LinkedStructuredType<FreightOrder, FreightOrder_> {
  String ID = "ID";

  String STATUS_CODE = "status_code";

  String CDS_NAME = "tms.FreightOrder";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  ElementRef<Instant> modifiedAt();

  ElementRef<String> modifiedBy();

  ElementRef<String> displayId();

  FreightOrderStatus_ status();

  FreightOrderStatus_ status(Function<FreightOrderStatus_, CqnPredicate> filter);

  @CdsName(STATUS_CODE)
  ElementRef<String> status_code();

  FreightOrderItem_ items();

  FreightOrderItem_ items(Function<FreightOrderItem_, CqnPredicate> filter);

  FreightOrderStop_ stops();

  FreightOrderStop_ stops(Function<FreightOrderStop_, CqnPredicate> filter);
}

package cds.gen.freightorderdomainservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.time.Instant;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderDomainService.FreightOrders")
@Generated("cds-maven-plugin")
public interface FreightOrders_ extends LinkedStructuredType<FreightOrders, FreightOrders_> {
  String ID = "ID";

  String STATUS_CODE = "status_code";

  String CDS_NAME = "FreightOrderDomainService.FreightOrders";

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

  FreightOrderItems_ items();

  FreightOrderItems_ items(Function<FreightOrderItems_, CqnPredicate> filter);

  FreightOrderStops_ stops();

  FreightOrderStops_ stops(Function<FreightOrderStops_, CqnPredicate> filter);
}

package cds.gen.transportationdemandservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandService.FreightOrders")
@Generated("cds-maven-plugin")
public interface FreightOrders_ extends LinkedStructuredType<FreightOrders, FreightOrders_> {
  String ID = "ID";

  String STATUS_CODE = "status_code";

  String CDS_NAME = "TransportationDemandService.FreightOrders";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<String> displayId();

  FreightOrderStatus_ status();

  FreightOrderStatus_ status(Function<FreightOrderStatus_, CqnPredicate> filter);

  @CdsName(STATUS_CODE)
  ElementRef<String> status_code();
}

package cds.gen.freightorderservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.time.Instant;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderService.UnassignedTransportationDemands")
@Generated("cds-maven-plugin")
public interface UnassignedTransportationDemands_ extends LinkedStructuredType<UnassignedTransportationDemands, UnassignedTransportationDemands_> {
  String ID = "ID";

  String FROM_LOCATION_ID = "fromLocation_ID";

  String TO_LOCATION_ID = "toLocation_ID";

  String CDS_NAME = "FreightOrderService.UnassignedTransportationDemands";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<String> displayId();

  Locations_ fromLocation();

  Locations_ fromLocation(Function<Locations_, CqnPredicate> filter);

  @CdsName(FROM_LOCATION_ID)
  ElementRef<String> fromLocation_ID();

  Locations_ toLocation();

  Locations_ toLocation(Function<Locations_, CqnPredicate> filter);

  @CdsName(TO_LOCATION_ID)
  ElementRef<String> toLocation_ID();

  ElementRef<Instant> deliveryDateTime();

  TransportationDemandItem_ items();

  TransportationDemandItem_ items(Function<TransportationDemandItem_, CqnPredicate> filter);
}

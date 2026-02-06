package cds.gen.transportationdemanddomainservice;

import cds.gen.tms.FreightOrder_;
import cds.gen.tms.Location_;
import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.time.Instant;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandDomainService.TransportationDemands")
@Generated("cds-maven-plugin")
public interface TransportationDemands_ extends LinkedStructuredType<TransportationDemands, TransportationDemands_> {
  String ID = "ID";

  String FROM_LOCATION_ID = "fromLocation_ID";

  String TO_LOCATION_ID = "toLocation_ID";

  String FREIGHT_ORDER_ID = "freightOrder_ID";

  String CDS_NAME = "TransportationDemandDomainService.TransportationDemands";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  ElementRef<Instant> modifiedAt();

  ElementRef<String> modifiedBy();

  ElementRef<String> displayId();

  Location_ fromLocation();

  Location_ fromLocation(Function<Location_, CqnPredicate> filter);

  @CdsName(FROM_LOCATION_ID)
  ElementRef<String> fromLocation_ID();

  Location_ toLocation();

  Location_ toLocation(Function<Location_, CqnPredicate> filter);

  @CdsName(TO_LOCATION_ID)
  ElementRef<String> toLocation_ID();

  ElementRef<Instant> deliveryDateTime();

  FreightOrder_ freightOrder();

  FreightOrder_ freightOrder(Function<FreightOrder_, CqnPredicate> filter);

  @CdsName(FREIGHT_ORDER_ID)
  ElementRef<String> freightOrder_ID();

  TransportationDemandItems_ items();

  TransportationDemandItems_ items(Function<TransportationDemandItems_, CqnPredicate> filter);
}

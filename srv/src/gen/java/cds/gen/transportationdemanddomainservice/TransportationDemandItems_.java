package cds.gen.transportationdemanddomainservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Integer;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandDomainService.TransportationDemandItems")
@Generated("cds-maven-plugin")
public interface TransportationDemandItems_ extends LinkedStructuredType<TransportationDemandItems, TransportationDemandItems_> {
  String ID = "ID";

  String TRANSPORTATION_DEMAND_ID = "transportationDemand_ID";

  String CDS_NAME = "TransportationDemandDomainService.TransportationDemandItems";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<String> displayId();

  TransportationDemands_ transportationDemand();

  TransportationDemands_ transportationDemand(
      Function<TransportationDemands_, CqnPredicate> filter);

  @CdsName(TRANSPORTATION_DEMAND_ID)
  ElementRef<String> transportationDemand_ID();

  ElementRef<String> productName();

  ElementRef<Integer> quantity();
}

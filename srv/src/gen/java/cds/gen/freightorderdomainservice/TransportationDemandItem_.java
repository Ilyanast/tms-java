package cds.gen.freightorderdomainservice;

import cds.gen.tms.TransportationDemand_;
import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Integer;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderDomainService.TransportationDemandItem")
@Generated("cds-maven-plugin")
public interface TransportationDemandItem_ extends LinkedStructuredType<TransportationDemandItem, TransportationDemandItem_> {
  String ID = "ID";

  String TRANSPORTATION_DEMAND_ID = "transportationDemand_ID";

  String CDS_NAME = "FreightOrderDomainService.TransportationDemandItem";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<String> displayId();

  TransportationDemand_ transportationDemand();

  TransportationDemand_ transportationDemand(Function<TransportationDemand_, CqnPredicate> filter);

  @CdsName(TRANSPORTATION_DEMAND_ID)
  ElementRef<String> transportationDemand_ID();

  ElementRef<String> productName();

  ElementRef<Integer> quantity();
}

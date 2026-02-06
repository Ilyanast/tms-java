package cds.gen.freightorderdomainservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Integer;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderDomainService.FreightOrderItems")
@Generated("cds-maven-plugin")
public interface FreightOrderItems_ extends LinkedStructuredType<FreightOrderItems, FreightOrderItems_> {
  String ID = "ID";

  String FREIGHT_ORDER_ID = "freightOrder_ID";

  String TRANSPORTATION_DEMAND_ITEM_ID = "transportationDemandItem_ID";

  String CDS_NAME = "FreightOrderDomainService.FreightOrderItems";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<String> displayId();

  FreightOrders_ freightOrder();

  FreightOrders_ freightOrder(Function<FreightOrders_, CqnPredicate> filter);

  @CdsName(FREIGHT_ORDER_ID)
  ElementRef<String> freightOrder_ID();

  ElementRef<String> productName();

  ElementRef<Integer> quantity();

  TransportationDemandItem_ transportationDemandItem();

  TransportationDemandItem_ transportationDemandItem(
      Function<TransportationDemandItem_, CqnPredicate> filter);

  @CdsName(TRANSPORTATION_DEMAND_ITEM_ID)
  ElementRef<String> transportationDemandItem_ID();

  ElementRef<String> tdDisplayId();
}

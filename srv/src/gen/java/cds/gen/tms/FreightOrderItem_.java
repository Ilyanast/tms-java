package cds.gen.tms;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Integer;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("tms.FreightOrderItem")
@Generated("cds-maven-plugin")
public interface FreightOrderItem_ extends LinkedStructuredType<FreightOrderItem, FreightOrderItem_> {
  String ID = "ID";

  String FREIGHT_ORDER_ID = "freightOrder_ID";

  String TRANSPORTATION_DEMAND_ITEM_ID = "transportationDemandItem_ID";

  String CDS_NAME = "tms.FreightOrderItem";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<String> displayId();

  FreightOrder_ freightOrder();

  FreightOrder_ freightOrder(Function<FreightOrder_, CqnPredicate> filter);

  @CdsName(FREIGHT_ORDER_ID)
  ElementRef<String> freightOrder_ID();

  ElementRef<String> productName();

  ElementRef<Integer> quantity();

  TransportationDemandItem_ transportationDemandItem();

  TransportationDemandItem_ transportationDemandItem(
      Function<TransportationDemandItem_, CqnPredicate> filter);

  @CdsName(TRANSPORTATION_DEMAND_ITEM_ID)
  ElementRef<String> transportationDemandItem_ID();
}

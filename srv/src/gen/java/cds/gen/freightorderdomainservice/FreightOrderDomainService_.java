package cds.gen.freightorderdomainservice;

import com.sap.cds.ql.CdsName;
import java.lang.Class;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("cds-maven-plugin")
@CdsName("FreightOrderDomainService")
public interface FreightOrderDomainService_ {
  String CDS_NAME = "FreightOrderDomainService";

  Class<FreightOrderItems_> FREIGHT_ORDER_ITEMS = FreightOrderItems_.class;

  Class<TransportationDemandItem_> TRANSPORTATION_DEMAND_ITEM = TransportationDemandItem_.class;

  Class<FreightOrderStatus_> FREIGHT_ORDER_STATUS = FreightOrderStatus_.class;

  Class<FreightOrderStops_> FREIGHT_ORDER_STOPS = FreightOrderStops_.class;

  Class<FreightOrderStatusTexts_> FREIGHT_ORDER_STATUS_TEXTS = FreightOrderStatusTexts_.class;

  Class<FreightOrders_> FREIGHT_ORDERS = FreightOrders_.class;
}

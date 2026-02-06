package cds.gen.freightorderservice;

import com.sap.cds.ql.CdsName;
import java.lang.Class;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("cds-maven-plugin")
@CdsName("FreightOrderService")
public interface FreightOrderService_ {
  String CDS_NAME = "FreightOrderService";

  Class<DraftAdministrativeData_> DRAFT_ADMINISTRATIVE_DATA = DraftAdministrativeData_.class;

  Class<UnassignedTransportationDemands_> UNASSIGNED_TRANSPORTATION_DEMANDS = UnassignedTransportationDemands_.class;

  Class<FreightOrderStatuses_> FREIGHT_ORDER_STATUSES = FreightOrderStatuses_.class;

  Class<FreightOrderStops_> FREIGHT_ORDER_STOPS = FreightOrderStops_.class;

  Class<FreightOrderItems_> FREIGHT_ORDER_ITEMS = FreightOrderItems_.class;

  Class<TransportationDemandItem_> TRANSPORTATION_DEMAND_ITEM = TransportationDemandItem_.class;

  Class<FreightOrders_> FREIGHT_ORDERS = FreightOrders_.class;

  Class<Locations_> LOCATIONS = Locations_.class;

  Class<FreightOrderStatusesTexts_> FREIGHT_ORDER_STATUSES_TEXTS = FreightOrderStatusesTexts_.class;
}

package cds.gen.transportationdemandservice;

import com.sap.cds.ql.CdsName;
import java.lang.Class;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("cds-maven-plugin")
@CdsName("TransportationDemandService")
public interface TransportationDemandService_ {
  String CDS_NAME = "TransportationDemandService";

  Class<TransportationDemands_> TRANSPORTATION_DEMANDS = TransportationDemands_.class;

  Class<FreightOrders_> FREIGHT_ORDERS = FreightOrders_.class;

  Class<TransportationDemandItems_> TRANSPORTATION_DEMAND_ITEMS = TransportationDemandItems_.class;

  Class<Locations_> LOCATIONS = Locations_.class;

  Class<DraftAdministrativeData_> DRAFT_ADMINISTRATIVE_DATA = DraftAdministrativeData_.class;

  Class<FreightOrderStatus_> FREIGHT_ORDER_STATUS = FreightOrderStatus_.class;

  Class<FreightOrderStatusTexts_> FREIGHT_ORDER_STATUS_TEXTS = FreightOrderStatusTexts_.class;
}

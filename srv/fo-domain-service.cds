using {tms} from '../db/schema';

service FreightOrderDomainService {

  entity FreightOrders     as
    projection on tms.FreightOrder {
      *,
      items : redirected to FreightOrderItems,
      stops : redirected to FreightOrderStops
    }
    actions {
      action assignTD(tdDisplayId: String);
      action unassignTD(tdId: UUID);
      action setStatus(newStatusCode: String);
    };

  entity FreightOrderItems as
    projection on tms.FreightOrderItem {
      *,
      transportationDemandItem.transportationDemand.displayId as tdDisplayId : String @readonly
    };

  entity FreightOrderStops as projection on tms.FreightOrderStop;
}

using {tms} from '../db/schema';

service FreightOrderDomainService {

    entity FreightOrders     as
        projection on tms.FreightOrder {
            *,
            items : redirected to FreightOrderItems,
            stops : redirected to FreightOrderStops
        }
        actions {
            action assignTD(tdDisplayId: String)    returns FreightOrders;
            action unassignTD(tdId: UUID)           returns FreightOrders;
            action setStatus(newStatusCode: String) returns FreightOrders;
        };

    entity FreightOrderItems as projection on tms.FreightOrderItem {
        *,
        transportationDemandItem.transportationDemand.displayId as tdDisplayId : String @readonly
    };

    entity FreightOrderStops as projection on tms.FreightOrderStop;
}

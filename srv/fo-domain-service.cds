using {tms} from '../db/schema';

service FreightOrderDomainService {

    entity FreightOrders     as
        projection on tms.FreightOrder {
            *,
            items : redirected to FreightOrderItems,
            stops : redirected to FreightOrderStops
        };

    entity FreightOrderItems as projection on tms.FreightOrderItem {
        *,
        transportationDemandItem.transportationDemand.displayId as tdDisplayId : String @readonly
    };

    entity FreightOrderStops as projection on tms.FreightOrderStop;

    action assignTD(ID: UUID,
                    tdDisplayId: String)    returns FreightOrders;

    action unassignTD(ID: UUID,
                      tdId: UUID)           returns FreightOrders;

    action setStatus(ID: UUID,
                     newStatusCode: String) returns FreightOrders;
}

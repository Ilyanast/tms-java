using {tms} from '../db/schema';

service FreightOrderService @(path: '/api/fo') {
    @odata.draft.enabled
    entity FreightOrders as
        projection on tms.FreightOrder {
            *,
            items : redirected to FreightOrderItems,
            stops : redirected to FreightOrderStops,
            (status.code = 'IN_PLANNING') as isInPlanning : Boolean @readonly,
            (status.code = 'IN_EXECUTION') as isInExecution : Boolean @readonly
        }
        actions {
            action assignTD(tdDisplayId : String)    returns FreightOrders;
            action unassignTD(tdId : UUID)           returns FreightOrders;
            action setStatus(newStatusCode : String) returns FreightOrders;
        };

    entity FreightOrderItems as
        projection on tms.FreightOrderItem {
            *,
            transportationDemandItem.transportationDemand.displayId as tdDisplayId : String
        };

    @readonly
    entity FreightOrderStops as
        projection on tms.FreightOrderStop {
            *,
            location : redirected to Locations
        };

    @readonly
    entity FreightOrderStatuses as projection on tms.FreightOrderStatus;

    @readonly
    entity Locations as projection on tms.Location;

    @readonly
    entity UnassignedTransportationDemands as
        select from tms.TransportationDemand {
            ID,
            displayId,
            fromLocation,
            toLocation,
            deliveryDateTime,
            items
        }
        where
            freightOrder is null;
}

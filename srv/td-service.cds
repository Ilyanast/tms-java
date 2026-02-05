using {tms} from '../db/schema';

service TransportationDemandService @(path: '/api/td') {
    @odata.draft.enabled
    entity TransportationDemands     as
        projection on tms.TransportationDemand {
            *,
            items                                         : redirected to TransportationDemandItems,
            freightOrder.displayId as assignedFODisplayId : String  @readonly,
            (
                freightOrder is not null
            )                      as isAssigned          : Boolean @readonly
        }
        actions {
            action assign(freightOrderId: UUID) returns TransportationDemands;
            action unassign()                   returns TransportationDemands;
        };

    entity TransportationDemandItems as projection on tms.TransportationDemandItem;

    @readonly
    entity Locations                 as projection on tms.Location;

    @readonly
    entity FreightOrders             as
        projection on tms.FreightOrder {
            ID,
            displayId,
            status
        };
}

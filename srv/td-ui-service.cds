using {TransportationDemandDomainService as Domain} from './td-domain-service';

using {tms} from '../db/schema';

service TransportationDemandUIService @(path: '/api/td') {

    @odata.draft.enabled
    entity TransportationDemands as
        projection on Domain.TransportationDemands {
            *,
            freightOrder.displayId     as assignedFODisplayId : String  @readonly,
            (freightOrder is not null) as isAssigned          : Boolean @readonly
        }
        actions {
            action assign(freightOrderId: UUID);
            action unassign();
        };

    @readonly
    entity Locations                   as projection on tms.Location;

    @readonly
    entity FreightOrders               as
        projection on tms.FreightOrder {
            ID,
            displayId,
            status.name as statusName : String
        }
        where
            status.code != 'IN_EXECUTION';
}

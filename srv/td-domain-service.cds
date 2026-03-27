using {tms} from '../db/schema';

service TransportationDemandDomainService {
    entity TransportationDemands     as
        projection on tms.TransportationDemand {
            *,
            items : redirected to TransportationDemandItems,
        }
        actions {
            action assign(freightOrderId: UUID);
            action unassign();
        };

    entity TransportationDemandItems as projection on tms.TransportationDemandItem;
}

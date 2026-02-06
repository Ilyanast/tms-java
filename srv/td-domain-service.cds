using {tms} from '../db/schema';

service TransportationDemandDomainService {
    entity TransportationDemands     as
        projection on tms.TransportationDemand {
            *,
            items : redirected to TransportationDemandItems,
        };

    entity TransportationDemandItems as projection on tms.TransportationDemandItem;

    action assign(ID: UUID, freightOrderId: UUID) returns TransportationDemands;

    action unassign(ID: UUID)                     returns TransportationDemands;
}

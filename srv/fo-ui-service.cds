using {FreightOrderDomainService as Domain} from './fo-domain-service';
using {tms} from '../db/schema';

service FreightOrderUIService @(path: '/api/fo') {

  @odata.draft.enabled
  entity FreightOrders                   as
    projection on Domain.FreightOrders {
      *,
      (
        status.code = 'IN_PLANNING'
      ) as isInPlanning  : Boolean @readonly,

      (
        status.code = 'IN_EXECUTION'
      ) as isInExecution : Boolean @readonly
    }
    actions {
      action assignTD(tdDisplayId: String);
      action unassignTD(tdId: UUID);
      action setStatus(newStatusCode: String);
    };

  @readonly
  entity FreightOrderStatuses            as projection on tms.FreightOrderStatus;

  @readonly
  entity Locations                       as projection on tms.Location;

  @readonly
  @cds.redirection.target
  entity UnassignedTransportationDemands as
    select from tms.TransportationDemand {
      ID,
      displayId,
      fromLocation,
      toLocation,
      deliveryDateTime
    }
    where
      freightOrder is null;

  @readonly
  entity AssignedTransportationDemands   as
    select from tms.TransportationDemand {
      ID,
      displayId,
    }
    where
      freightOrder is not null;
}

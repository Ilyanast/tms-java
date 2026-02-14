using {
    cuid,
    managed,
    sap.common.CodeList
} from '@sap/cds/common';

namespace tms;

entity FreightOrderStatus : CodeList {
    key code : String(20);
}

entity Location : cuid, managed {
    displayId : String(10)  @readonly;
    name      : String(100) @mandatory;
}

entity TransportationDemand : cuid, managed {
    displayId        : String(10)               @readonly;
    fromLocation     : Association to Location  @mandatory  @assert.target;
    toLocation       : Association to Location  @mandatory  @assert.target;
    deliveryDateTime : DateTime                 @mandatory;
    freightOrder     : Association to FreightOrder;
    items            : Composition of many TransportationDemandItem
                           on items.transportationDemand = $self;
}

entity TransportationDemandItem : cuid {
    displayId            : String(10);
    transportationDemand : Association to TransportationDemand @mandatory;
    productName          : String(100)                         @mandatory;
    quantity             : Integer                             @mandatory;
}

entity FreightOrder : cuid, managed {
    displayId : String(10) @readonly;
    status    : Association to FreightOrderStatus default 'IN_PLANNING';
    items     : Composition of many FreightOrderItem
                    on items.freightOrder = $self;
    stops     : Composition of many FreightOrderStop
                    on stops.freightOrder = $self;
}

entity FreightOrderItem : cuid {
    displayId                : String(10)                  @readonly;
    freightOrder             : Association to FreightOrder @mandatory;
    productName              : String(100);
    quantity                 : Integer;
    transportationDemandItem : Association to TransportationDemandItem;
}

entity FreightOrderStop : cuid {
    freightOrder : Association to FreightOrder @mandatory;
    location     : Association to Location     @mandatory;
    sequence     : Integer                     @mandatory;
}

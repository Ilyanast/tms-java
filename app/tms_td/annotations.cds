using TransportationDemandUIService as service from '../../srv/td-ui-service';

annotate service.TransportationDemands with @(
  UI.UpdateHidden: isAssigned,
  UI.DeleteHidden: isAssigned,
  UI             : {
    HeaderInfo         : {
      TypeName      : 'Transportation Demand',
      TypeNamePlural: 'Transportation Demands',
      Title         : {Value: displayId},
      Description   : {Value: deliveryDateTime}
    },

    SelectionFields    : [
      fromLocation_ID,
      toLocation_ID,
      deliveryDateTime
    ],

    LineItem           : [
      {
        Value: displayId,
        Label: 'Display ID'
      },
      {
        Value: fromLocation.name,
        Label: 'From Location'
      },
      {
        Value: toLocation.name,
        Label: 'To Location'
      },
      {
        Value: deliveryDateTime,
        Label: 'Delivery Date & Time'
      },
      {
        Value: assignedFODisplayId,
        Label: 'Assigned FO'
      }
    ],

    Facets             : [
      {
        $Type : 'UI.ReferenceFacet',
        Target: '@UI.FieldGroup#General',
        Label : 'General Information'
      },
      {
        $Type : 'UI.ReferenceFacet',
        Target: 'items/@UI.LineItem',
        Label : 'Items'
      }
    ],

    FieldGroup #General: {Data: [
      {
        Value: displayId,
        Label: 'Display ID'
      },
      {
        Value: assignedFODisplayId,
        Label: 'Assigned FO'
      },
      {
        Value: fromLocation_ID,
        Label: 'From Location'
      },
      {
        Value: toLocation_ID,
        Label: 'To Location'
      },
      {
        Value: deliveryDateTime,
        Label: 'Delivery Date/Time'
      }
    ]},

    Identification     : [
      {
        $Type        : 'UI.DataFieldForAction',
        Action       : 'assign',
        Label        : 'Assign',
        ![@UI.Hidden]: isAssigned
      },
      {
        $Type        : 'UI.DataFieldForAction',
        Action       : 'unassign',
        Label        : 'Unassign',
        ![@UI.Hidden]: {$edmJson: {$Not: {$Path: 'isAssigned'}}}
      }
    ]
  }
);

annotate service.TransportationDemands with {
  fromLocation @(Common: {
    Text           : fromLocation.name,
    TextArrangement: #TextFirst,
    ValueList      : {
      Label         : 'Locations',
      CollectionPath: 'Locations',
      Parameters    : [
        {
          $Type            : 'Common.ValueListParameterInOut',
          LocalDataProperty: fromLocation_ID,
          ValueListProperty: 'ID'
        },
        {
          $Type            : 'Common.ValueListParameterDisplayOnly',
          ValueListProperty: 'displayId'
        },
        {
          $Type            : 'Common.ValueListParameterDisplayOnly',
          ValueListProperty: 'name'
        }
      ]
    }
  });

  toLocation   @(Common: {
    Text           : toLocation.name,
    TextArrangement: #TextFirst,
    ValueList      : {
      Label         : 'Locations',
      CollectionPath: 'Locations',
      Parameters    : [
        {
          $Type            : 'Common.ValueListParameterInOut',
          LocalDataProperty: toLocation_ID,
          ValueListProperty: 'ID'
        },
        {
          $Type            : 'Common.ValueListParameterDisplayOnly',
          ValueListProperty: 'displayId'
        },
        {
          $Type            : 'Common.ValueListParameterDisplayOnly',
          ValueListProperty: 'name'
        }
      ]
    }
  });
};

annotate service.TransportationDemands actions {
  assign(freightOrderId @(
    title           : 'Freight Order',
    Common.ValueList: {
      Label         : 'Freight Orders',
      CollectionPath: 'FreightOrders',
      Parameters    : [
        {
          $Type            : 'Common.ValueListParameterInOut',
          LocalDataProperty: freightOrderId,
          ValueListProperty: 'ID'
        },
        {
          $Type            : 'Common.ValueListParameterDisplayOnly',
          ValueListProperty: 'displayId'
        },
        {
          $Type            : 'Common.ValueListParameterDisplayOnly',
          ValueListProperty: 'statusName'
        }
      ]
    }
  )
  );

  assign                @Common.SideEffects: {TargetEntities: [
    'assignedFODisplayId',
    'isAssigned'
  ]};
  unassign              @Common.SideEffects: {TargetEntities: [
    'assignedFODisplayId',
    'isAssigned'
  ]};
};

annotate service.TransportationDemandItems with {
  displayId @readonly;
};

annotate service.TransportationDemandItems with @(UI: {
  HeaderInfo             : {
    TypeName      : 'Item',
    TypeNamePlural: 'Items',
    Title         : {Value: productName}
  },

  LineItem               : [
    {
      Value: displayId,
      Label: 'Display ID'
    },
    {
      Value: productName,
      Label: 'Product'
    },
    {
      Value: quantity,
      Label: 'Quantity'
    }
  ],

  Facets                 : [{
    $Type : 'UI.ReferenceFacet',
    Target: '@UI.FieldGroup#ItemDetails',
    Label : 'Item Details'
  }],

  FieldGroup #ItemDetails: {Data: [
    {
      Value: displayId,
      Label: 'Display ID'
    },
    {
      Value: productName,
      Label: 'Product Name'
    },
    {
      Value: quantity,
      Label: 'Quantity'
    }
  ]}
});

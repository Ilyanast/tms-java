using FreightOrderUIService as service from '../../srv/fo-ui-service';

annotate service.FreightOrders with @(
  UI.UpdateHidden: true,
  UI.DeleteHidden: isInExecution,
  UI             : {
    HeaderInfo         : {
      TypeName      : 'Freight Order',
      TypeNamePlural: 'Freight Orders',
      Title         : {Value: displayId},
      Description   : {Value: status.name}
    },

    SelectionFields    : [
      displayId,
      status_code
    ],

    LineItem           : [
      {
        Value: displayId,
        Label: 'Display ID'
      },
      {
        Value: status.name,
        Label: 'Status'
      },
      {
        Value: createdAt,
        Label: 'Created'
      },
      {
        Value: modifiedAt,
        Label: 'Modified'
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
        Target: 'items/@com.sap.vocabularies.UI.v1.LineItem',
        Label : 'Items'
      },
    ],

    FieldGroup #General: {Data: [
      {
        Value: displayId,
        Label: 'Display ID'
      },
      {
        Value: status_code,
        Label: 'Status'
      },
      {
        Value: createdAt,
        Label: 'Created At'
      },
      {
        Value: modifiedAt,
        Label: 'Modified At'
      }
    ]},

    Identification     : [
      {
        $Type        : 'UI.DataFieldForAction',
        Action       : 'assignTD',
        Label        : 'Assign TD',
        ![@UI.Hidden]: isInExecution
      },
      {
        $Type        : 'UI.DataFieldForAction',
        Action       : 'unassignTD',
        Label        : 'Unassign TD',
        ![@UI.Hidden]: isInExecution
      },
      {
        $Type        : 'UI.DataFieldForAction',
        Action       : 'setStatus',
        Label        : 'Set Status',
        ![@UI.Hidden]: isInExecution
      }
    ]
  }
);

annotate service.FreightOrders with {
  ID     @UI.Hidden;
  status @(Common: {
    Text           : status.name,
    TextArrangement: #TextOnly,
    ValueList      : {
      Label         : 'Statuses',
      CollectionPath: 'FreightOrderStatuses',
      Parameters    : [
        {
          $Type            : 'Common.ValueListParameterInOut',
          LocalDataProperty: status_code,
          ValueListProperty: 'code'
        },
        {
          $Type            : 'Common.ValueListParameterDisplayOnly',
          ValueListProperty: 'name'
        }
      ]
    }
  });
};

annotate service.FreightOrders actions {
  assignTD(tdDisplayId    @(
    title           : 'Transportation Demand ID',
    description     : 'Enter Display ID of unassigned Transportation Demand',
    Common.ValueList: {
      Label         : 'Unassigned Transportation Demands',
      CollectionPath: 'UnassignedTransportationDemands',
      Parameters    : [
        {
          $Type            : 'Common.ValueListParameterInOut',
          LocalDataProperty: tdDisplayId,
          ValueListProperty: 'displayId'
        },
        {
          $Type            : 'Common.ValueListParameterDisplayOnly',
          ValueListProperty: 'fromLocation/name'
        },
        {
          $Type            : 'Common.ValueListParameterDisplayOnly',
          ValueListProperty: 'toLocation/name'
        },
        {
          $Type            : 'Common.ValueListParameterDisplayOnly',
          ValueListProperty: 'deliveryDateTime'
        }
      ]
    }
  )
  );

  unassignTD(tdId         @(Common.ValueList: {
    CollectionPath: 'AssignedTransportationDemands',
    Parameters    : [
      {
        $Type            : 'Common.ValueListParameterInOut',
        LocalDataProperty: tdId,
        ValueListProperty: 'ID'
      },
      {
        $Type            : 'Common.ValueListParameterDisplayOnly',
        ValueListProperty: 'displayId'
      }
    ]
  })
  );

  setStatus(newStatusCode @(
    title           : 'New Status',
    description     : 'Select new status for the Freight Order',
    Common.ValueList: {
      Label         : 'Statuses',
      CollectionPath: 'FreightOrderStatuses',
      Parameters    : [
        {
          $Type            : 'Common.ValueListParameterInOut',
          LocalDataProperty: newStatusCode,
          ValueListProperty: 'code'
        },
        {
          $Type            : 'Common.ValueListParameterDisplayOnly',
          ValueListProperty: 'name'
        },
        {
          $Type            : 'Common.ValueListParameterDisplayOnly',
          ValueListProperty: 'descr'
        }
      ]
    }
  )
  );
};

annotate service.FreightOrderItems with @(
  UI.CreateHidden: true,
  UI.UpdateHidden: true,
  UI.DeleteHidden: true,
  UI             : {
    HeaderInfo             : {
      TypeName      : 'Item',
      TypeNamePlural: 'Items',
      Title         : {Value: productName},
      Description   : {Value: tdDisplayId}
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
      },
      {
        Value: tdDisplayId,
        Label: 'TD ID'
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
      },
      {
        Value: tdDisplayId,
        Label: 'Transportation Demand'
      }
    ]}
  }
);

annotate service.FreightOrderItems with {
  ID                       @UI.Hidden;
  freightOrder             @UI.Hidden;
  transportationDemandItem @UI.Hidden;
};

annotate service.FreightOrders actions {
  assignTD   @Common.SideEffects: {TargetEntities: [
    'items',
    'stops'
  ]};

  unassignTD @Common.SideEffects: {TargetEntities: [
    'items',
    'stops'
  ]};
};

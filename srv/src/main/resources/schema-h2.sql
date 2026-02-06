
DROP VIEW IF EXISTS localized_FreightOrderUIService_FreightOrderStatus;
DROP VIEW IF EXISTS localized_FreightOrderUIService_FreightOrderStatuses;
DROP VIEW IF EXISTS localized_FreightOrderDomainService_FreightOrderStatus;
DROP VIEW IF EXISTS TransportationDemandUIService_TransportationDemandItems;
DROP VIEW IF EXISTS TransportationDemandUIService_TransportationDemands;
DROP VIEW IF EXISTS FreightOrderUIService_FreightOrderStops;
DROP VIEW IF EXISTS FreightOrderUIService_TransportationDemandItem;
DROP VIEW IF EXISTS FreightOrderUIService_FreightOrderItems;
DROP VIEW IF EXISTS FreightOrderUIService_FreightOrderStatus_texts;
DROP VIEW IF EXISTS FreightOrderUIService_FreightOrderStatus;
DROP VIEW IF EXISTS FreightOrderUIService_FreightOrders;
DROP VIEW IF EXISTS TransportationDemandUIService_DraftAdministrativeData;
DROP VIEW IF EXISTS FreightOrderUIService_DraftAdministrativeData;
DROP VIEW IF EXISTS localized_tms_FreightOrderStatus;
DROP VIEW IF EXISTS TransportationDemandUIService_FreightOrders;
DROP VIEW IF EXISTS TransportationDemandUIService_Locations;
DROP VIEW IF EXISTS TransportationDemandDomainService_TransportationDemandItems;
DROP VIEW IF EXISTS TransportationDemandDomainService_TransportationDemands;
DROP VIEW IF EXISTS FreightOrderUIService_AssignedTransportationDemands;
DROP VIEW IF EXISTS FreightOrderUIService_FreightOrderStatuses_texts;
DROP VIEW IF EXISTS FreightOrderUIService_FreightOrderStatuses;
DROP VIEW IF EXISTS FreightOrderUIService_Locations;
DROP VIEW IF EXISTS FreightOrderUIService_UnassignedTransportationDemands;
DROP VIEW IF EXISTS FreightOrderDomainService_FreightOrderStops;
DROP VIEW IF EXISTS FreightOrderDomainService_TransportationDemandItem;
DROP VIEW IF EXISTS FreightOrderDomainService_FreightOrderItems;
DROP VIEW IF EXISTS FreightOrderDomainService_FreightOrderStatus_texts;
DROP VIEW IF EXISTS FreightOrderDomainService_FreightOrderStatus;
DROP VIEW IF EXISTS FreightOrderDomainService_FreightOrders;
DROP TABLE IF EXISTS TransportationDemandUIService_TransportationDemandItems_drafts;
DROP TABLE IF EXISTS TransportationDemandUIService_TransportationDemands_drafts;
DROP TABLE IF EXISTS FreightOrderUIService_FreightOrderStops_drafts;
DROP TABLE IF EXISTS FreightOrderUIService_FreightOrderItems_drafts;
DROP TABLE IF EXISTS FreightOrderUIService_FreightOrders_drafts;
DROP TABLE IF EXISTS DRAFT_DraftAdministrativeData;
DROP TABLE IF EXISTS cds_outbox_Messages;
DROP TABLE IF EXISTS tms_FreightOrderStop;
DROP TABLE IF EXISTS tms_Location;
DROP TABLE IF EXISTS tms_TransportationDemand;
DROP TABLE IF EXISTS tms_TransportationDemandItem;
DROP TABLE IF EXISTS tms_FreightOrderItem;
DROP TABLE IF EXISTS tms_FreightOrderStatus_texts;
DROP TABLE IF EXISTS tms_FreightOrderStatus;
DROP TABLE IF EXISTS tms_FreightOrder;

CREATE TABLE tms_FreightOrder (
  ID NVARCHAR(36) NOT NULL,
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  modifiedAt TIMESTAMP(7),
  modifiedBy NVARCHAR(255),
  displayId NVARCHAR(10),
  status_code NVARCHAR(20) DEFAULT 'IN_PLANNING',
  PRIMARY KEY(ID)
);

CREATE TABLE tms_FreightOrderStatus (
  name NVARCHAR(255),
  descr NVARCHAR(1000),
  code NVARCHAR(20) NOT NULL,
  PRIMARY KEY(code)
);

CREATE TABLE tms_FreightOrderStatus_texts (
  locale NVARCHAR(14) NOT NULL,
  name NVARCHAR(255),
  descr NVARCHAR(1000),
  code NVARCHAR(20) NOT NULL,
  PRIMARY KEY(locale, code)
);

CREATE TABLE tms_FreightOrderItem (
  ID NVARCHAR(36) NOT NULL,
  displayId NVARCHAR(10),
  freightOrder_ID NVARCHAR(36),
  productName NVARCHAR(100),
  quantity INTEGER,
  transportationDemandItem_ID NVARCHAR(36),
  PRIMARY KEY(ID)
);

CREATE TABLE tms_TransportationDemandItem (
  ID NVARCHAR(36) NOT NULL,
  displayId NVARCHAR(10),
  transportationDemand_ID NVARCHAR(36),
  productName NVARCHAR(100),
  quantity INTEGER,
  PRIMARY KEY(ID)
);

CREATE TABLE tms_TransportationDemand (
  ID NVARCHAR(36) NOT NULL,
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  modifiedAt TIMESTAMP(7),
  modifiedBy NVARCHAR(255),
  displayId NVARCHAR(10),
  fromLocation_ID NVARCHAR(36),
  toLocation_ID NVARCHAR(36),
  deliveryDateTime TIMESTAMP(0),
  freightOrder_ID NVARCHAR(36),
  PRIMARY KEY(ID)
);

CREATE TABLE tms_Location (
  ID NVARCHAR(36) NOT NULL,
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  modifiedAt TIMESTAMP(7),
  modifiedBy NVARCHAR(255),
  displayId NVARCHAR(10),
  name NVARCHAR(100),
  PRIMARY KEY(ID)
);

CREATE TABLE tms_FreightOrderStop (
  ID NVARCHAR(36) NOT NULL,
  freightOrder_ID NVARCHAR(36),
  location_ID NVARCHAR(36),
  sequence INTEGER,
  PRIMARY KEY(ID)
);

CREATE TABLE cds_outbox_Messages (
  ID NVARCHAR(36) NOT NULL,
  timestamp TIMESTAMP(7),
  target NVARCHAR(255),
  msg NCLOB,
  attempts INTEGER DEFAULT 0,
  "PARTITION" INTEGER DEFAULT 0,
  lastError NCLOB,
  lastAttemptTimestamp TIMESTAMP(7),
  status NVARCHAR(23),
  task NVARCHAR(255),
  appid NVARCHAR(255),
  PRIMARY KEY(ID)
);

CREATE TABLE DRAFT_DraftAdministrativeData (
  DraftUUID NVARCHAR(36) NOT NULL,
  CreationDateTime TIMESTAMP(7),
  CreatedByUser NVARCHAR(256),
  CreatedByUserDescription NVARCHAR(256),
  DraftIsCreatedByMe BOOLEAN,
  LastChangeDateTime TIMESTAMP(7),
  LastChangedByUser NVARCHAR(256),
  LastChangedByUserDescription NVARCHAR(256),
  InProcessByUser NVARCHAR(256),
  InProcessByUserDescription NVARCHAR(256),
  DraftIsProcessedByMe BOOLEAN,
  DraftMessages NCLOB,
  PRIMARY KEY(DraftUUID)
);

CREATE TABLE FreightOrderUIService_FreightOrders_drafts (
  ID NVARCHAR(36) NOT NULL,
  createdAt TIMESTAMP(7) NULL,
  createdBy NVARCHAR(255) NULL,
  modifiedAt TIMESTAMP(7) NULL,
  modifiedBy NVARCHAR(255) NULL,
  displayId NVARCHAR(10) NULL,
  status_code NVARCHAR(20) NULL DEFAULT 'IN_PLANNING',
  isInPlanning BOOLEAN NULL,
  isInExecution BOOLEAN NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL,
  PRIMARY KEY(ID)
);

CREATE TABLE FreightOrderUIService_FreightOrderItems_drafts (
  ID NVARCHAR(36) NOT NULL,
  displayId NVARCHAR(10) NULL,
  freightOrder_ID NVARCHAR(36) NULL,
  productName NVARCHAR(100) NULL,
  quantity INTEGER NULL,
  transportationDemandItem_ID NVARCHAR(36) NULL,
  tdDisplayId NVARCHAR(255) NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL,
  PRIMARY KEY(ID)
);

CREATE TABLE FreightOrderUIService_FreightOrderStops_drafts (
  ID NVARCHAR(36) NOT NULL,
  freightOrder_ID NVARCHAR(36) NULL,
  location_ID NVARCHAR(36) NULL,
  sequence INTEGER NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL,
  PRIMARY KEY(ID)
);

CREATE TABLE TransportationDemandUIService_TransportationDemands_drafts (
  ID NVARCHAR(36) NOT NULL,
  createdAt TIMESTAMP(7) NULL,
  createdBy NVARCHAR(255) NULL,
  modifiedAt TIMESTAMP(7) NULL,
  modifiedBy NVARCHAR(255) NULL,
  displayId NVARCHAR(10) NULL,
  fromLocation_ID NVARCHAR(36) NULL,
  toLocation_ID NVARCHAR(36) NULL,
  deliveryDateTime TIMESTAMP(0) NULL,
  freightOrder_ID NVARCHAR(36) NULL,
  assignedFODisplayId NVARCHAR(255) NULL,
  isAssigned BOOLEAN NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL,
  PRIMARY KEY(ID)
);

CREATE TABLE TransportationDemandUIService_TransportationDemandItems_drafts (
  ID NVARCHAR(36) NOT NULL,
  displayId NVARCHAR(10) NULL,
  transportationDemand_ID NVARCHAR(36) NULL,
  productName NVARCHAR(100) NULL,
  quantity INTEGER NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL,
  PRIMARY KEY(ID)
);

CREATE VIEW FreightOrderDomainService_FreightOrders AS SELECT
  FreightOrder_0.ID,
  FreightOrder_0.createdAt,
  FreightOrder_0.createdBy,
  FreightOrder_0.modifiedAt,
  FreightOrder_0.modifiedBy,
  FreightOrder_0.displayId,
  FreightOrder_0.status_code
FROM tms_FreightOrder AS FreightOrder_0;

CREATE VIEW FreightOrderDomainService_FreightOrderStatus AS SELECT
  FreightOrderStatus_0.name,
  FreightOrderStatus_0.descr,
  FreightOrderStatus_0.code
FROM tms_FreightOrderStatus AS FreightOrderStatus_0;

CREATE VIEW FreightOrderDomainService_FreightOrderStatus_texts AS SELECT
  texts_0.locale,
  texts_0.name,
  texts_0.descr,
  texts_0.code
FROM tms_FreightOrderStatus_texts AS texts_0;

CREATE VIEW FreightOrderDomainService_FreightOrderItems AS SELECT
  FreightOrderItem_0.ID,
  FreightOrderItem_0.displayId,
  FreightOrderItem_0.freightOrder_ID,
  FreightOrderItem_0.productName,
  FreightOrderItem_0.quantity,
  FreightOrderItem_0.transportationDemandItem_ID,
  transportationDemand_2.displayId AS tdDisplayId
FROM ((tms_FreightOrderItem AS FreightOrderItem_0 LEFT JOIN tms_TransportationDemandItem AS transportationDemandItem_1 ON FreightOrderItem_0.transportationDemandItem_ID = transportationDemandItem_1.ID) LEFT JOIN tms_TransportationDemand AS transportationDemand_2 ON transportationDemandItem_1.transportationDemand_ID = transportationDemand_2.ID);

CREATE VIEW FreightOrderDomainService_TransportationDemandItem AS SELECT
  TransportationDemandItem_0.ID,
  TransportationDemandItem_0.displayId,
  TransportationDemandItem_0.transportationDemand_ID,
  TransportationDemandItem_0.productName,
  TransportationDemandItem_0.quantity
FROM tms_TransportationDemandItem AS TransportationDemandItem_0;

CREATE VIEW FreightOrderDomainService_FreightOrderStops AS SELECT
  FreightOrderStop_0.ID,
  FreightOrderStop_0.freightOrder_ID,
  FreightOrderStop_0.location_ID,
  FreightOrderStop_0.sequence
FROM tms_FreightOrderStop AS FreightOrderStop_0;

CREATE VIEW FreightOrderUIService_UnassignedTransportationDemands AS SELECT
  TransportationDemand_0.ID,
  TransportationDemand_0.displayId,
  TransportationDemand_0.fromLocation_ID,
  TransportationDemand_0.toLocation_ID,
  TransportationDemand_0.deliveryDateTime
FROM tms_TransportationDemand AS TransportationDemand_0
WHERE TransportationDemand_0.freightOrder_ID IS NULL;

CREATE VIEW FreightOrderUIService_Locations AS SELECT
  Location_0.ID,
  Location_0.createdAt,
  Location_0.createdBy,
  Location_0.modifiedAt,
  Location_0.modifiedBy,
  Location_0.displayId,
  Location_0.name
FROM tms_Location AS Location_0;

CREATE VIEW FreightOrderUIService_FreightOrderStatuses AS SELECT
  FreightOrderStatus_0.name,
  FreightOrderStatus_0.descr,
  FreightOrderStatus_0.code
FROM tms_FreightOrderStatus AS FreightOrderStatus_0;

CREATE VIEW FreightOrderUIService_FreightOrderStatuses_texts AS SELECT
  texts_0.locale,
  texts_0.name,
  texts_0.descr,
  texts_0.code
FROM tms_FreightOrderStatus_texts AS texts_0;

CREATE VIEW FreightOrderUIService_AssignedTransportationDemands AS SELECT
  TransportationDemand_0.ID,
  TransportationDemand_0.displayId
FROM tms_TransportationDemand AS TransportationDemand_0
WHERE TransportationDemand_0.freightOrder_ID IS NOT NULL;

CREATE VIEW TransportationDemandDomainService_TransportationDemands AS SELECT
  TransportationDemand_0.ID,
  TransportationDemand_0.createdAt,
  TransportationDemand_0.createdBy,
  TransportationDemand_0.modifiedAt,
  TransportationDemand_0.modifiedBy,
  TransportationDemand_0.displayId,
  TransportationDemand_0.fromLocation_ID,
  TransportationDemand_0.toLocation_ID,
  TransportationDemand_0.deliveryDateTime,
  TransportationDemand_0.freightOrder_ID
FROM tms_TransportationDemand AS TransportationDemand_0;

CREATE VIEW TransportationDemandDomainService_TransportationDemandItems AS SELECT
  TransportationDemandItem_0.ID,
  TransportationDemandItem_0.displayId,
  TransportationDemandItem_0.transportationDemand_ID,
  TransportationDemandItem_0.productName,
  TransportationDemandItem_0.quantity
FROM tms_TransportationDemandItem AS TransportationDemandItem_0;

CREATE VIEW TransportationDemandUIService_Locations AS SELECT
  Location_0.ID,
  Location_0.createdAt,
  Location_0.createdBy,
  Location_0.modifiedAt,
  Location_0.modifiedBy,
  Location_0.displayId,
  Location_0.name
FROM tms_Location AS Location_0;

CREATE VIEW TransportationDemandUIService_FreightOrders AS SELECT
  FreightOrder_0.ID,
  FreightOrder_0.displayId,
  status_1.name AS statusName
FROM (tms_FreightOrder AS FreightOrder_0 LEFT JOIN tms_FreightOrderStatus AS status_1 ON FreightOrder_0.status_code = status_1.code)
WHERE FreightOrder_0.status_code IS DISTINCT FROM 'IN_EXECUTION';

CREATE VIEW localized_tms_FreightOrderStatus AS SELECT
  coalesce(localized_1.name, L_0.name) AS name,
  coalesce(localized_1.descr, L_0.descr) AS descr,
  L_0.code
FROM (tms_FreightOrderStatus AS L_0 LEFT JOIN tms_FreightOrderStatus_texts AS localized_1 ON localized_1.code = L_0.code AND localized_1.locale = @locale);

CREATE VIEW FreightOrderUIService_DraftAdministrativeData AS SELECT
  DraftAdministrativeData.DraftUUID,
  DraftAdministrativeData.CreationDateTime,
  DraftAdministrativeData.CreatedByUser,
  DraftAdministrativeData.CreatedByUserDescription,
  DraftAdministrativeData.DraftIsCreatedByMe,
  DraftAdministrativeData.LastChangeDateTime,
  DraftAdministrativeData.LastChangedByUser,
  DraftAdministrativeData.LastChangedByUserDescription,
  DraftAdministrativeData.InProcessByUser,
  DraftAdministrativeData.InProcessByUserDescription,
  DraftAdministrativeData.DraftIsProcessedByMe,
  DraftAdministrativeData.DraftMessages
FROM DRAFT_DraftAdministrativeData AS DraftAdministrativeData;

CREATE VIEW TransportationDemandUIService_DraftAdministrativeData AS SELECT
  DraftAdministrativeData.DraftUUID,
  DraftAdministrativeData.CreationDateTime,
  DraftAdministrativeData.CreatedByUser,
  DraftAdministrativeData.CreatedByUserDescription,
  DraftAdministrativeData.DraftIsCreatedByMe,
  DraftAdministrativeData.LastChangeDateTime,
  DraftAdministrativeData.LastChangedByUser,
  DraftAdministrativeData.LastChangedByUserDescription,
  DraftAdministrativeData.InProcessByUser,
  DraftAdministrativeData.InProcessByUserDescription,
  DraftAdministrativeData.DraftIsProcessedByMe,
  DraftAdministrativeData.DraftMessages
FROM DRAFT_DraftAdministrativeData AS DraftAdministrativeData;

CREATE VIEW FreightOrderUIService_FreightOrders AS SELECT
  FreightOrders_0.ID,
  FreightOrders_0.createdAt,
  FreightOrders_0.createdBy,
  FreightOrders_0.modifiedAt,
  FreightOrders_0.modifiedBy,
  FreightOrders_0.displayId,
  FreightOrders_0.status_code,
  FreightOrders_0.status_code = 'IN_PLANNING' AS isInPlanning,
  FreightOrders_0.status_code = 'IN_EXECUTION' AS isInExecution
FROM FreightOrderDomainService_FreightOrders AS FreightOrders_0;

CREATE VIEW FreightOrderUIService_FreightOrderStatus AS SELECT
  FreightOrderStatus_0.name,
  FreightOrderStatus_0.descr,
  FreightOrderStatus_0.code
FROM FreightOrderDomainService_FreightOrderStatus AS FreightOrderStatus_0;

CREATE VIEW FreightOrderUIService_FreightOrderStatus_texts AS SELECT
  texts_0.locale,
  texts_0.name,
  texts_0.descr,
  texts_0.code
FROM FreightOrderDomainService_FreightOrderStatus_texts AS texts_0;

CREATE VIEW FreightOrderUIService_FreightOrderItems AS SELECT
  FreightOrderItems_0.ID,
  FreightOrderItems_0.displayId,
  FreightOrderItems_0.freightOrder_ID,
  FreightOrderItems_0.productName,
  FreightOrderItems_0.quantity,
  FreightOrderItems_0.transportationDemandItem_ID,
  FreightOrderItems_0.tdDisplayId
FROM FreightOrderDomainService_FreightOrderItems AS FreightOrderItems_0;

CREATE VIEW FreightOrderUIService_TransportationDemandItem AS SELECT
  TransportationDemandItem_0.ID,
  TransportationDemandItem_0.displayId,
  TransportationDemandItem_0.transportationDemand_ID,
  TransportationDemandItem_0.productName,
  TransportationDemandItem_0.quantity
FROM FreightOrderDomainService_TransportationDemandItem AS TransportationDemandItem_0;

CREATE VIEW FreightOrderUIService_FreightOrderStops AS SELECT
  FreightOrderStops_0.ID,
  FreightOrderStops_0.freightOrder_ID,
  FreightOrderStops_0.location_ID,
  FreightOrderStops_0.sequence
FROM FreightOrderDomainService_FreightOrderStops AS FreightOrderStops_0;

CREATE VIEW TransportationDemandUIService_TransportationDemands AS SELECT
  TransportationDemands_0.ID,
  TransportationDemands_0.createdAt,
  TransportationDemands_0.createdBy,
  TransportationDemands_0.modifiedAt,
  TransportationDemands_0.modifiedBy,
  TransportationDemands_0.displayId,
  TransportationDemands_0.fromLocation_ID,
  TransportationDemands_0.toLocation_ID,
  TransportationDemands_0.deliveryDateTime,
  TransportationDemands_0.freightOrder_ID,
  freightOrder_1.displayId AS assignedFODisplayId,
  TransportationDemands_0.freightOrder_ID IS NOT NULL AS isAssigned
FROM (TransportationDemandDomainService_TransportationDemands AS TransportationDemands_0 LEFT JOIN tms_FreightOrder AS freightOrder_1 ON TransportationDemands_0.freightOrder_ID = freightOrder_1.ID);

CREATE VIEW TransportationDemandUIService_TransportationDemandItems AS SELECT
  TransportationDemandItems_0.ID,
  TransportationDemandItems_0.displayId,
  TransportationDemandItems_0.transportationDemand_ID,
  TransportationDemandItems_0.productName,
  TransportationDemandItems_0.quantity
FROM TransportationDemandDomainService_TransportationDemandItems AS TransportationDemandItems_0;

CREATE VIEW localized_FreightOrderDomainService_FreightOrderStatus AS SELECT
  FreightOrderStatus_0.name,
  FreightOrderStatus_0.descr,
  FreightOrderStatus_0.code
FROM localized_tms_FreightOrderStatus AS FreightOrderStatus_0;

CREATE VIEW localized_FreightOrderUIService_FreightOrderStatuses AS SELECT
  FreightOrderStatus_0.name,
  FreightOrderStatus_0.descr,
  FreightOrderStatus_0.code
FROM localized_tms_FreightOrderStatus AS FreightOrderStatus_0;

CREATE VIEW localized_FreightOrderUIService_FreightOrderStatus AS SELECT
  FreightOrderStatus_0.name,
  FreightOrderStatus_0.descr,
  FreightOrderStatus_0.code
FROM localized_FreightOrderDomainService_FreightOrderStatus AS FreightOrderStatus_0;

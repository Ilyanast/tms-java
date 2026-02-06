package cds.gen.transportationdemandservice;

import cds.gen.draft.DraftAdministrativeDataDraftMessage;
import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandService.TransportationDemands")
@Generated("cds-maven-plugin")
public interface TransportationDemands extends CdsData {
  String ID = "ID";

  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String MODIFIED_AT = "modifiedAt";

  String MODIFIED_BY = "modifiedBy";

  String DISPLAY_ID = "displayId";

  String FROM_LOCATION = "fromLocation";

  String FROM_LOCATION_ID = "fromLocation_ID";

  String TO_LOCATION = "toLocation";

  String TO_LOCATION_ID = "toLocation_ID";

  String DELIVERY_DATE_TIME = "deliveryDateTime";

  String FREIGHT_ORDER = "freightOrder";

  String FREIGHT_ORDER_ID = "freightOrder_ID";

  String ITEMS = "items";

  String ASSIGNED_FODISPLAY_ID = "assignedFODisplayId";

  String IS_ASSIGNED = "isAssigned";

  String IS_ACTIVE_ENTITY = "IsActiveEntity";

  String HAS_ACTIVE_ENTITY = "HasActiveEntity";

  String HAS_DRAFT_ENTITY = "HasDraftEntity";

  String DRAFT_ADMINISTRATIVE_DATA = "DraftAdministrativeData";

  String DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID = "DraftAdministrativeData_DraftUUID";

  String SIBLING_ENTITY = "SiblingEntity";

  String DRAFT_MESSAGES = "DraftMessages";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  Instant getCreatedAt();

  void setCreatedAt(Instant createdAt);

  String getCreatedBy();

  void setCreatedBy(String createdBy);

  Instant getModifiedAt();

  void setModifiedAt(Instant modifiedAt);

  String getModifiedBy();

  void setModifiedBy(String modifiedBy);

  String getDisplayId();

  void setDisplayId(String displayId);

  Locations getFromLocation();

  void setFromLocation(Map<String, ?> fromLocation);

  @CdsName(FROM_LOCATION_ID)
  String getFromLocationId();

  @CdsName(FROM_LOCATION_ID)
  void setFromLocationId(String fromLocationId);

  Locations getToLocation();

  void setToLocation(Map<String, ?> toLocation);

  @CdsName(TO_LOCATION_ID)
  String getToLocationId();

  @CdsName(TO_LOCATION_ID)
  void setToLocationId(String toLocationId);

  Instant getDeliveryDateTime();

  void setDeliveryDateTime(Instant deliveryDateTime);

  FreightOrders getFreightOrder();

  void setFreightOrder(Map<String, ?> freightOrder);

  @CdsName(FREIGHT_ORDER_ID)
  String getFreightOrderId();

  @CdsName(FREIGHT_ORDER_ID)
  void setFreightOrderId(String freightOrderId);

  List<TransportationDemandItems> getItems();

  void setItems(List<? extends Map<String, ?>> items);

  String getAssignedFODisplayId();

  void setAssignedFODisplayId(String assignedFODisplayId);

  Boolean getIsAssigned();

  void setIsAssigned(Boolean isAssigned);

  @CdsName(IS_ACTIVE_ENTITY)
  Boolean getIsActiveEntity();

  @CdsName(IS_ACTIVE_ENTITY)
  void setIsActiveEntity(Boolean isActiveEntity);

  @CdsName(HAS_ACTIVE_ENTITY)
  Boolean getHasActiveEntity();

  @CdsName(HAS_ACTIVE_ENTITY)
  void setHasActiveEntity(Boolean hasActiveEntity);

  @CdsName(HAS_DRAFT_ENTITY)
  Boolean getHasDraftEntity();

  @CdsName(HAS_DRAFT_ENTITY)
  void setHasDraftEntity(Boolean hasDraftEntity);

  @CdsName(DRAFT_ADMINISTRATIVE_DATA)
  DraftAdministrativeData getDraftAdministrativeData();

  @CdsName(DRAFT_ADMINISTRATIVE_DATA)
  void setDraftAdministrativeData(Map<String, ?> draftAdministrativeData);

  @CdsName(DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID)
  String getDraftAdministrativeDataDraftUUID();

  @CdsName(DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID)
  void setDraftAdministrativeDataDraftUUID(String draftAdministrativeDataDraftUUID);

  @CdsName(SIBLING_ENTITY)
  TransportationDemands getSiblingEntity();

  @CdsName(SIBLING_ENTITY)
  void setSiblingEntity(Map<String, ?> siblingEntity);

  @CdsName(DRAFT_MESSAGES)
  Collection<DraftAdministrativeDataDraftMessage> getDraftMessages();

  @CdsName(DRAFT_MESSAGES)
  void setDraftMessages(Collection<DraftAdministrativeDataDraftMessage> draftMessages);

  TransportationDemands_ ref();

  static TransportationDemands create() {
    return Struct.create(TransportationDemands.class);
  }

  static TransportationDemands of(Map<String, Object> map) {
    return Struct.access(map).as(TransportationDemands.class);
  }
}

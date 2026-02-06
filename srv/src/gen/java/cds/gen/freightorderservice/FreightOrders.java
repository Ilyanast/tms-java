package cds.gen.freightorderservice;

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

@CdsName("FreightOrderService.FreightOrders")
@Generated("cds-maven-plugin")
public interface FreightOrders extends CdsData {
  String ID = "ID";

  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String MODIFIED_AT = "modifiedAt";

  String MODIFIED_BY = "modifiedBy";

  String DISPLAY_ID = "displayId";

  String STATUS = "status";

  String STATUS_CODE = "status_code";

  String ITEMS = "items";

  String STOPS = "stops";

  String IS_IN_PLANNING = "isInPlanning";

  String IS_IN_EXECUTION = "isInExecution";

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

  FreightOrderStatuses getStatus();

  void setStatus(Map<String, ?> status);

  @CdsName(STATUS_CODE)
  String getStatusCode();

  @CdsName(STATUS_CODE)
  void setStatusCode(String statusCode);

  List<FreightOrderItems> getItems();

  void setItems(List<? extends Map<String, ?>> items);

  List<FreightOrderStops> getStops();

  void setStops(List<? extends Map<String, ?>> stops);

  Boolean getIsInPlanning();

  void setIsInPlanning(Boolean isInPlanning);

  Boolean getIsInExecution();

  void setIsInExecution(Boolean isInExecution);

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
  FreightOrders getSiblingEntity();

  @CdsName(SIBLING_ENTITY)
  void setSiblingEntity(Map<String, ?> siblingEntity);

  @CdsName(DRAFT_MESSAGES)
  Collection<DraftAdministrativeDataDraftMessage> getDraftMessages();

  @CdsName(DRAFT_MESSAGES)
  void setDraftMessages(Collection<DraftAdministrativeDataDraftMessage> draftMessages);

  FreightOrders_ ref();

  static FreightOrders create() {
    return Struct.create(FreightOrders.class);
  }

  static FreightOrders of(Map<String, Object> map) {
    return Struct.access(map).as(FreightOrders.class);
  }
}

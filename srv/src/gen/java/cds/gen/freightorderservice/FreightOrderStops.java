package cds.gen.freightorderservice;

import cds.gen.draft.DraftAdministrativeDataDraftMessage;
import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.Collection;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderService.FreightOrderStops")
@Generated("cds-maven-plugin")
public interface FreightOrderStops extends CdsData {
  String ID = "ID";

  String FREIGHT_ORDER = "freightOrder";

  String FREIGHT_ORDER_ID = "freightOrder_ID";

  String LOCATION = "location";

  String LOCATION_ID = "location_ID";

  String SEQUENCE = "sequence";

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

  FreightOrders getFreightOrder();

  void setFreightOrder(Map<String, ?> freightOrder);

  @CdsName(FREIGHT_ORDER_ID)
  String getFreightOrderId();

  @CdsName(FREIGHT_ORDER_ID)
  void setFreightOrderId(String freightOrderId);

  Locations getLocation();

  void setLocation(Map<String, ?> location);

  @CdsName(LOCATION_ID)
  String getLocationId();

  @CdsName(LOCATION_ID)
  void setLocationId(String locationId);

  Integer getSequence();

  void setSequence(Integer sequence);

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
  FreightOrderStops getSiblingEntity();

  @CdsName(SIBLING_ENTITY)
  void setSiblingEntity(Map<String, ?> siblingEntity);

  @CdsName(DRAFT_MESSAGES)
  Collection<DraftAdministrativeDataDraftMessage> getDraftMessages();

  @CdsName(DRAFT_MESSAGES)
  void setDraftMessages(Collection<DraftAdministrativeDataDraftMessage> draftMessages);

  FreightOrderStops_ ref();

  static FreightOrderStops create() {
    return Struct.create(FreightOrderStops.class);
  }

  static FreightOrderStops of(Map<String, Object> map) {
    return Struct.access(map).as(FreightOrderStops.class);
  }
}

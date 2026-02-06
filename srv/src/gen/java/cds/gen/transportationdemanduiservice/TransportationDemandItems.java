package cds.gen.transportationdemanduiservice;

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

@CdsName("TransportationDemandUIService.TransportationDemandItems")
@Generated("cds-maven-plugin")
public interface TransportationDemandItems extends CdsData {
  String ID = "ID";

  String DISPLAY_ID = "displayId";

  String TRANSPORTATION_DEMAND = "transportationDemand";

  String TRANSPORTATION_DEMAND_ID = "transportationDemand_ID";

  String PRODUCT_NAME = "productName";

  String QUANTITY = "quantity";

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

  String getDisplayId();

  void setDisplayId(String displayId);

  TransportationDemands getTransportationDemand();

  void setTransportationDemand(Map<String, ?> transportationDemand);

  @CdsName(TRANSPORTATION_DEMAND_ID)
  String getTransportationDemandId();

  @CdsName(TRANSPORTATION_DEMAND_ID)
  void setTransportationDemandId(String transportationDemandId);

  String getProductName();

  void setProductName(String productName);

  Integer getQuantity();

  void setQuantity(Integer quantity);

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
  TransportationDemandItems getSiblingEntity();

  @CdsName(SIBLING_ENTITY)
  void setSiblingEntity(Map<String, ?> siblingEntity);

  @CdsName(DRAFT_MESSAGES)
  Collection<DraftAdministrativeDataDraftMessage> getDraftMessages();

  @CdsName(DRAFT_MESSAGES)
  void setDraftMessages(Collection<DraftAdministrativeDataDraftMessage> draftMessages);

  TransportationDemandItems_ ref();

  static TransportationDemandItems create() {
    return Struct.create(TransportationDemandItems.class);
  }

  static TransportationDemandItems of(Map<String, Object> map) {
    return Struct.access(map).as(TransportationDemandItems.class);
  }
}

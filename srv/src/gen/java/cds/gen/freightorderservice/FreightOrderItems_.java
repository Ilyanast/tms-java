package cds.gen.freightorderservice;

import cds.gen.draft.DraftAdministrativeDataDraftMessage;
import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.Collection;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("FreightOrderService.FreightOrderItems")
@Generated("cds-maven-plugin")
public interface FreightOrderItems_ extends LinkedStructuredType<FreightOrderItems, FreightOrderItems_> {
  String ID = "ID";

  String FREIGHT_ORDER_ID = "freightOrder_ID";

  String TRANSPORTATION_DEMAND_ITEM_ID = "transportationDemandItem_ID";

  String IS_ACTIVE_ENTITY = "IsActiveEntity";

  String HAS_ACTIVE_ENTITY = "HasActiveEntity";

  String HAS_DRAFT_ENTITY = "HasDraftEntity";

  String DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID = "DraftAdministrativeData_DraftUUID";

  String DRAFT_MESSAGES = "DraftMessages";

  String CDS_NAME = "FreightOrderService.FreightOrderItems";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<String> displayId();

  FreightOrders_ freightOrder();

  FreightOrders_ freightOrder(Function<FreightOrders_, CqnPredicate> filter);

  @CdsName(FREIGHT_ORDER_ID)
  ElementRef<String> freightOrder_ID();

  ElementRef<String> productName();

  ElementRef<Integer> quantity();

  TransportationDemandItem_ transportationDemandItem();

  TransportationDemandItem_ transportationDemandItem(
      Function<TransportationDemandItem_, CqnPredicate> filter);

  @CdsName(TRANSPORTATION_DEMAND_ITEM_ID)
  ElementRef<String> transportationDemandItem_ID();

  ElementRef<String> tdDisplayId();

  @CdsName(IS_ACTIVE_ENTITY)
  ElementRef<Boolean> IsActiveEntity();

  @CdsName(HAS_ACTIVE_ENTITY)
  ElementRef<Boolean> HasActiveEntity();

  @CdsName(HAS_DRAFT_ENTITY)
  ElementRef<Boolean> HasDraftEntity();

  DraftAdministrativeData_ DraftAdministrativeData();

  DraftAdministrativeData_ DraftAdministrativeData(
      Function<DraftAdministrativeData_, CqnPredicate> filter);

  @CdsName(DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID)
  ElementRef<String> DraftAdministrativeData_DraftUUID();

  FreightOrderItems_ SiblingEntity();

  FreightOrderItems_ SiblingEntity(Function<FreightOrderItems_, CqnPredicate> filter);

  @CdsName(DRAFT_MESSAGES)
  ElementRef<Collection<DraftAdministrativeDataDraftMessage>> DraftMessages();
}

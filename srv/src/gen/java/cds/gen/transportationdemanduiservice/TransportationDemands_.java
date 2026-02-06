package cds.gen.transportationdemanduiservice;

import cds.gen.draft.DraftAdministrativeDataDraftMessage;
import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Boolean;
import java.lang.String;
import java.time.Instant;
import java.util.Collection;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("TransportationDemandUIService.TransportationDemands")
@Generated("cds-maven-plugin")
public interface TransportationDemands_ extends LinkedStructuredType<TransportationDemands, TransportationDemands_> {
  String ID = "ID";

  String FROM_LOCATION_ID = "fromLocation_ID";

  String TO_LOCATION_ID = "toLocation_ID";

  String FREIGHT_ORDER_ID = "freightOrder_ID";

  String IS_ACTIVE_ENTITY = "IsActiveEntity";

  String HAS_ACTIVE_ENTITY = "HasActiveEntity";

  String HAS_DRAFT_ENTITY = "HasDraftEntity";

  String DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID = "DraftAdministrativeData_DraftUUID";

  String DRAFT_MESSAGES = "DraftMessages";

  String CDS_NAME = "TransportationDemandUIService.TransportationDemands";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  ElementRef<Instant> modifiedAt();

  ElementRef<String> modifiedBy();

  ElementRef<String> displayId();

  Locations_ fromLocation();

  Locations_ fromLocation(Function<Locations_, CqnPredicate> filter);

  @CdsName(FROM_LOCATION_ID)
  ElementRef<String> fromLocation_ID();

  Locations_ toLocation();

  Locations_ toLocation(Function<Locations_, CqnPredicate> filter);

  @CdsName(TO_LOCATION_ID)
  ElementRef<String> toLocation_ID();

  ElementRef<Instant> deliveryDateTime();

  FreightOrders_ freightOrder();

  FreightOrders_ freightOrder(Function<FreightOrders_, CqnPredicate> filter);

  @CdsName(FREIGHT_ORDER_ID)
  ElementRef<String> freightOrder_ID();

  TransportationDemandItems_ items();

  TransportationDemandItems_ items(Function<TransportationDemandItems_, CqnPredicate> filter);

  ElementRef<String> assignedFODisplayId();

  ElementRef<Boolean> isAssigned();

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

  TransportationDemands_ SiblingEntity();

  TransportationDemands_ SiblingEntity(Function<TransportationDemands_, CqnPredicate> filter);

  @CdsName(DRAFT_MESSAGES)
  ElementRef<Collection<DraftAdministrativeDataDraftMessage>> DraftMessages();
}

package cds.gen.transportationdemandservice;

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

@CdsName("TransportationDemandService.TransportationDemandItems")
@Generated("cds-maven-plugin")
public interface TransportationDemandItems_ extends LinkedStructuredType<TransportationDemandItems, TransportationDemandItems_> {
  String ID = "ID";

  String TRANSPORTATION_DEMAND_ID = "transportationDemand_ID";

  String IS_ACTIVE_ENTITY = "IsActiveEntity";

  String HAS_ACTIVE_ENTITY = "HasActiveEntity";

  String HAS_DRAFT_ENTITY = "HasDraftEntity";

  String DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID = "DraftAdministrativeData_DraftUUID";

  String DRAFT_MESSAGES = "DraftMessages";

  String CDS_NAME = "TransportationDemandService.TransportationDemandItems";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<String> displayId();

  TransportationDemands_ transportationDemand();

  TransportationDemands_ transportationDemand(
      Function<TransportationDemands_, CqnPredicate> filter);

  @CdsName(TRANSPORTATION_DEMAND_ID)
  ElementRef<String> transportationDemand_ID();

  ElementRef<String> productName();

  ElementRef<Integer> quantity();

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

  TransportationDemandItems_ SiblingEntity();

  TransportationDemandItems_ SiblingEntity(
      Function<TransportationDemandItems_, CqnPredicate> filter);

  @CdsName(DRAFT_MESSAGES)
  ElementRef<Collection<DraftAdministrativeDataDraftMessage>> DraftMessages();
}

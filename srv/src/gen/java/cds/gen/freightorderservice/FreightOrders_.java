package cds.gen.freightorderservice;

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

@CdsName("FreightOrderService.FreightOrders")
@Generated("cds-maven-plugin")
public interface FreightOrders_ extends LinkedStructuredType<FreightOrders, FreightOrders_> {
  String ID = "ID";

  String STATUS_CODE = "status_code";

  String IS_ACTIVE_ENTITY = "IsActiveEntity";

  String HAS_ACTIVE_ENTITY = "HasActiveEntity";

  String HAS_DRAFT_ENTITY = "HasDraftEntity";

  String DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID = "DraftAdministrativeData_DraftUUID";

  String DRAFT_MESSAGES = "DraftMessages";

  String CDS_NAME = "FreightOrderService.FreightOrders";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  ElementRef<Instant> modifiedAt();

  ElementRef<String> modifiedBy();

  ElementRef<String> displayId();

  FreightOrderStatuses_ status();

  FreightOrderStatuses_ status(Function<FreightOrderStatuses_, CqnPredicate> filter);

  @CdsName(STATUS_CODE)
  ElementRef<String> status_code();

  FreightOrderItems_ items();

  FreightOrderItems_ items(Function<FreightOrderItems_, CqnPredicate> filter);

  FreightOrderStops_ stops();

  FreightOrderStops_ stops(Function<FreightOrderStops_, CqnPredicate> filter);

  ElementRef<Boolean> isInPlanning();

  ElementRef<Boolean> isInExecution();

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

  FreightOrders_ SiblingEntity();

  FreightOrders_ SiblingEntity(Function<FreightOrders_, CqnPredicate> filter);

  @CdsName(DRAFT_MESSAGES)
  ElementRef<Collection<DraftAdministrativeDataDraftMessage>> DraftMessages();
}

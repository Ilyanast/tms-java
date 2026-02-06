package cds.gen.draft;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.Boolean;
import java.lang.String;
import java.time.Instant;
import java.util.Collection;
import javax.annotation.processing.Generated;

@CdsName("DRAFT.DraftAdministrativeData")
@Generated("cds-maven-plugin")
public interface DraftAdministrativeData_ extends LinkedStructuredType<DraftAdministrativeData, DraftAdministrativeData_> {
  String DRAFT_UUID = "DraftUUID";

  String CREATION_DATE_TIME = "CreationDateTime";

  String CREATED_BY_USER = "CreatedByUser";

  String DRAFT_IS_CREATED_BY_ME = "DraftIsCreatedByMe";

  String LAST_CHANGE_DATE_TIME = "LastChangeDateTime";

  String LAST_CHANGED_BY_USER = "LastChangedByUser";

  String IN_PROCESS_BY_USER = "InProcessByUser";

  String DRAFT_IS_PROCESSED_BY_ME = "DraftIsProcessedByMe";

  String DRAFT_MESSAGES = "DraftMessages";

  String CDS_NAME = "DRAFT.DraftAdministrativeData";

  @CdsName(DRAFT_UUID)
  ElementRef<String> DraftUUID();

  @CdsName(CREATION_DATE_TIME)
  ElementRef<Instant> CreationDateTime();

  @CdsName(CREATED_BY_USER)
  ElementRef<String> CreatedByUser();

  @CdsName(DRAFT_IS_CREATED_BY_ME)
  ElementRef<Boolean> DraftIsCreatedByMe();

  @CdsName(LAST_CHANGE_DATE_TIME)
  ElementRef<Instant> LastChangeDateTime();

  @CdsName(LAST_CHANGED_BY_USER)
  ElementRef<String> LastChangedByUser();

  @CdsName(IN_PROCESS_BY_USER)
  ElementRef<String> InProcessByUser();

  @CdsName(DRAFT_IS_PROCESSED_BY_ME)
  ElementRef<Boolean> DraftIsProcessedByMe();

  @CdsName(DRAFT_MESSAGES)
  ElementRef<Collection<DraftAdministrativeDataDraftMessage>> DraftMessages();
}

package cds.gen.draft;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("DRAFT.DraftAdministrativeData")
@Generated("cds-maven-plugin")
public interface DraftAdministrativeData extends CdsData {
  String DRAFT_UUID = "DraftUUID";

  String CREATION_DATE_TIME = "CreationDateTime";

  String CREATED_BY_USER = "CreatedByUser";

  String DRAFT_IS_CREATED_BY_ME = "DraftIsCreatedByMe";

  String LAST_CHANGE_DATE_TIME = "LastChangeDateTime";

  String LAST_CHANGED_BY_USER = "LastChangedByUser";

  String IN_PROCESS_BY_USER = "InProcessByUser";

  String DRAFT_IS_PROCESSED_BY_ME = "DraftIsProcessedByMe";

  String DRAFT_MESSAGES = "DraftMessages";

  @CdsName(DRAFT_UUID)
  String getDraftUUID();

  @CdsName(DRAFT_UUID)
  void setDraftUUID(String draftUUID);

  @CdsName(CREATION_DATE_TIME)
  Instant getCreationDateTime();

  @CdsName(CREATION_DATE_TIME)
  void setCreationDateTime(Instant creationDateTime);

  @CdsName(CREATED_BY_USER)
  String getCreatedByUser();

  @CdsName(CREATED_BY_USER)
  void setCreatedByUser(String createdByUser);

  @CdsName(DRAFT_IS_CREATED_BY_ME)
  Boolean getDraftIsCreatedByMe();

  @CdsName(DRAFT_IS_CREATED_BY_ME)
  void setDraftIsCreatedByMe(Boolean draftIsCreatedByMe);

  @CdsName(LAST_CHANGE_DATE_TIME)
  Instant getLastChangeDateTime();

  @CdsName(LAST_CHANGE_DATE_TIME)
  void setLastChangeDateTime(Instant lastChangeDateTime);

  @CdsName(LAST_CHANGED_BY_USER)
  String getLastChangedByUser();

  @CdsName(LAST_CHANGED_BY_USER)
  void setLastChangedByUser(String lastChangedByUser);

  @CdsName(IN_PROCESS_BY_USER)
  String getInProcessByUser();

  @CdsName(IN_PROCESS_BY_USER)
  void setInProcessByUser(String inProcessByUser);

  @CdsName(DRAFT_IS_PROCESSED_BY_ME)
  Boolean getDraftIsProcessedByMe();

  @CdsName(DRAFT_IS_PROCESSED_BY_ME)
  void setDraftIsProcessedByMe(Boolean draftIsProcessedByMe);

  @CdsName(DRAFT_MESSAGES)
  Collection<DraftAdministrativeDataDraftMessage> getDraftMessages();

  @CdsName(DRAFT_MESSAGES)
  void setDraftMessages(Collection<DraftAdministrativeDataDraftMessage> draftMessages);

  DraftAdministrativeData_ ref();

  static DraftAdministrativeData create() {
    return Struct.create(DraftAdministrativeData.class);
  }

  static DraftAdministrativeData of(Map<String, Object> map) {
    return Struct.access(map).as(DraftAdministrativeData.class);
  }

  static DraftAdministrativeData create(String draftUUID) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(DRAFT_UUID, draftUUID);
    return Struct.access(keys).as(DraftAdministrativeData.class);
  }
}

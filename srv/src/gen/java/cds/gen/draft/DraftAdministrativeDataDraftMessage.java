package cds.gen.draft;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Boolean;
import java.lang.Object;
import java.lang.Short;
import java.lang.String;
import java.util.Collection;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("DRAFT.DraftAdministrativeData_DraftMessage")
@Generated("cds-maven-plugin")
public interface DraftAdministrativeDataDraftMessage extends CdsData {
  String CODE = "code";

  String MESSAGE = "message";

  String TARGET = "target";

  String ADDITIONAL_TARGETS = "additionalTargets";

  String TRANSITION = "transition";

  String NUMERIC_SEVERITY = "numericSeverity";

  String LONGTEXT_URL = "longtextUrl";

  String getCode();

  void setCode(String code);

  String getMessage();

  void setMessage(String message);

  String getTarget();

  void setTarget(String target);

  Collection<String> getAdditionalTargets();

  void setAdditionalTargets(Collection<String> additionalTargets);

  Boolean getTransition();

  void setTransition(Boolean transition);

  Short getNumericSeverity();

  void setNumericSeverity(Short numericSeverity);

  String getLongtextUrl();

  void setLongtextUrl(String longtextUrl);

  static DraftAdministrativeDataDraftMessage create() {
    return Struct.create(DraftAdministrativeDataDraftMessage.class);
  }

  static DraftAdministrativeDataDraftMessage of(Map<String, Object> map) {
    return Struct.access(map).as(DraftAdministrativeDataDraftMessage.class);
  }
}

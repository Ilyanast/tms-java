package cds.gen.draft;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.Boolean;
import java.lang.Short;
import java.lang.String;
import java.util.Collection;
import javax.annotation.processing.Generated;

@CdsName("DRAFT.DraftAdministrativeData_DraftMessage")
@Generated("cds-maven-plugin")
public interface DraftAdministrativeDataDraftMessage_ extends LinkedStructuredType<DraftAdministrativeDataDraftMessage, DraftAdministrativeDataDraftMessage_> {
  String CDS_NAME = "DRAFT.DraftAdministrativeData_DraftMessage";

  ElementRef<String> code();

  ElementRef<String> message();

  ElementRef<String> target();

  ElementRef<Collection<String>> additionalTargets();

  ElementRef<Boolean> transition();

  ElementRef<Short> numericSeverity();

  ElementRef<String> longtextUrl();
}

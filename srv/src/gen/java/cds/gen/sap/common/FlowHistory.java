package cds.gen.sap.common;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("sap.common.FlowHistory")
@Generated("cds-maven-plugin")
public interface FlowHistory extends CdsData {
  String TRANSITIONS_ = "transitions_";

  @CdsName(TRANSITIONS_)
  List<Transitions> getTransitions();

  @CdsName(TRANSITIONS_)
  void setTransitions(List<Transitions> transitions);

  static FlowHistory create() {
    return Struct.create(FlowHistory.class);
  }

  static FlowHistory of(Map<String, Object> map) {
    return Struct.access(map).as(FlowHistory.class);
  }

  @CdsName("sap.common.FlowHistory.transitions_")
  interface Transitions extends CdsData {
    String TIMESTAMP = "timestamp";

    String USER = "user";

    String STATUS = "status";

    String COMMENT = "comment";

    Instant getTimestamp();

    void setTimestamp(Instant timestamp);

    String getUser();

    void setUser(String user);

    String getStatus();

    void setStatus(String status);

    String getComment();

    void setComment(String comment);

    static Transitions create() {
      return Struct.create(Transitions.class);
    }

    static Transitions of(Map<String, Object> map) {
      return Struct.access(map).as(Transitions.class);
    }
  }
}

package cds.gen.transportationdemandservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import java.time.Instant;
import javax.annotation.processing.Generated;

@EventName("createTD")
@Generated("cds-maven-plugin")
public interface CreateTDContext extends EventContext {
  String FROM_LOCATION_ID = "fromLocation_ID";

  String TO_LOCATION_ID = "toLocation_ID";

  String DELIVERY_DATE_TIME = "deliveryDateTime";

  String CDS_NAME = "createTD";

  @CdsName(FROM_LOCATION_ID)
  String getFromLocationId();

  @CdsName(FROM_LOCATION_ID)
  void setFromLocationId(String fromLocationId);

  @CdsName(TO_LOCATION_ID)
  String getToLocationId();

  @CdsName(TO_LOCATION_ID)
  void setToLocationId(String toLocationId);

  Instant getDeliveryDateTime();

  void setDeliveryDateTime(Instant deliveryDateTime);

  @Override
  TransportationDemandService getService();

  void setResult(TransportationDemands result);

  TransportationDemands getResult();

  static CreateTDContext create() {
    return EventContext.create(CreateTDContext.class, null);
  }
}

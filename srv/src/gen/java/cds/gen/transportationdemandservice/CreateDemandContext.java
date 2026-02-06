package cds.gen.transportationdemandservice;

import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Override;
import java.lang.String;
import java.time.Instant;
import javax.annotation.processing.Generated;

@EventName("createDemand")
@Generated("cds-maven-plugin")
public interface CreateDemandContext extends EventContext {
  String FROM_LOCATION_ID = "fromLocationId";

  String TO_LOCATION_ID = "toLocationId";

  String DELIVERY_DATE_TIME = "deliveryDateTime";

  String CDS_NAME = "createDemand";

  String getFromLocationId();

  void setFromLocationId(String fromLocationId);

  String getToLocationId();

  void setToLocationId(String toLocationId);

  Instant getDeliveryDateTime();

  void setDeliveryDateTime(Instant deliveryDateTime);

  @Override
  TransportationDemandService getService();

  void setResult(TransportationDemands result);

  TransportationDemands getResult();

  static CreateDemandContext create() {
    return EventContext.create(CreateDemandContext.class, null);
  }
}

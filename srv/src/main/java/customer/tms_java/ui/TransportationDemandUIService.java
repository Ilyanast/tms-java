package customer.tms_java.ui;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.ql.Select;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.transportationdemanduiservice.TransportationDemandUIService_;
import cds.gen.transportationdemanduiservice.TransportationDemands;
import cds.gen.transportationdemanduiservice.TransportationDemands_;

@Component
@ServiceName(TransportationDemandUIService_.CDS_NAME)
public class TransportationDemandUIService {

  private final CqnService service;

  public TransportationDemandUIService(@Qualifier(TransportationDemandUIService_.CDS_NAME) CqnService service) {
    this.service = service;
  }

  public TransportationDemands getTDById(String id) {
    return service.run(Select.from(TransportationDemands_.class).where(t -> t.ID().eq(id)))
        .single(TransportationDemands.class);
  }
}

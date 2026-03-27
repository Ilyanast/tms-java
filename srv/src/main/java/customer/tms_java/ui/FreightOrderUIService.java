package customer.tms_java.ui;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.ql.Select;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.freightorderuiservice.FreightOrderUIService_;
import cds.gen.freightorderuiservice.FreightOrders;
import cds.gen.freightorderuiservice.FreightOrders_;

@Component
@ServiceName(FreightOrderUIService_.CDS_NAME)
public class FreightOrderUIService {

  private final CqnService service;

  public FreightOrderUIService(@Qualifier(FreightOrderUIService_.CDS_NAME) CqnService service) {
    this.service = service;
  }

  public FreightOrders getFOById(String id) {
    return service.run(Select.from(FreightOrders_.class).where(f -> f.ID().eq(id))).single(FreightOrders.class);
  }
}

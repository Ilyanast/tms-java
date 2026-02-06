package cds.gen.tms;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Integer;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("tms.FreightOrderStop")
@Generated("cds-maven-plugin")
public interface FreightOrderStop_ extends LinkedStructuredType<FreightOrderStop, FreightOrderStop_> {
  String ID = "ID";

  String FREIGHT_ORDER_ID = "freightOrder_ID";

  String LOCATION_ID = "location_ID";

  String CDS_NAME = "tms.FreightOrderStop";

  @CdsName(ID)
  ElementRef<String> ID();

  FreightOrder_ freightOrder();

  FreightOrder_ freightOrder(Function<FreightOrder_, CqnPredicate> filter);

  @CdsName(FREIGHT_ORDER_ID)
  ElementRef<String> freightOrder_ID();

  Location_ location();

  Location_ location(Function<Location_, CqnPredicate> filter);

  @CdsName(LOCATION_ID)
  ElementRef<String> location_ID();

  ElementRef<Integer> sequence();
}

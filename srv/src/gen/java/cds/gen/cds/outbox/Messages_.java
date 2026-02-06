package cds.gen.cds.outbox;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.LinkedStructuredType;
import java.lang.Integer;
import java.lang.String;
import java.time.Instant;
import javax.annotation.processing.Generated;

@CdsName("cds.outbox.Messages")
@Generated("cds-maven-plugin")
public interface Messages_ extends LinkedStructuredType<Messages, Messages_> {
  String ID = "ID";

  String CDS_NAME = "cds.outbox.Messages";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<Instant> timestamp();

  ElementRef<String> target();

  ElementRef<String> msg();

  ElementRef<Integer> attempts();

  ElementRef<Integer> partition();

  ElementRef<String> lastError();

  ElementRef<Instant> lastAttemptTimestamp();

  ElementRef<String> status();

  ElementRef<String> task();

  ElementRef<String> appid();
}

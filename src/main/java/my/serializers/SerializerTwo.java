package my.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import my.beans.Bean1;
import my.beans.Bean2;

import java.io.IOException;

/**
 * Created by kkulagin on 4/22/2015.
 */
public class SerializerTwo extends StdSerializer<Bean2> {


  public SerializerTwo() {
    super(Bean2.class);
  }

  @Override
  public void serialize(Bean2 value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
    jgen.writeStartObject();
    jgen.writeNumberField("id", value.getId());
    jgen.writeStringField("bean2String", value.getBean2String());
    jgen.writeStringField("serializerTwo", "true");
    jgen.writeEndObject();
  }
}

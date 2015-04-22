package my.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import my.beans.Bean1;

import java.io.IOException;

/**
 * Created by kkulagin on 4/22/2015.
 */
public class SerializerOne extends StdSerializer<Bean1> {


  public SerializerOne() {
    super(Bean1.class);
  }

  @Override
  public void serialize(Bean1 value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {

  }
}

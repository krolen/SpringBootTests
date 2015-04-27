package my.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import my.beans.Bean1;

import java.io.IOException;

/**
 * Created by kkulagin on 4/17/2015.
 */
public class DeserializerOne extends StdDeserializer<Bean1> {


  public DeserializerOne() {
    super(Bean1.class);
  }



  @Override
  public Bean1 deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    Bean1 bean1 = new Bean1();
    bean1.setBean1String("DeserializerOne");
    return bean1;
  }
}

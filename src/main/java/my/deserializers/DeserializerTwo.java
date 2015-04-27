package my.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import my.beans.Bean1;
import my.beans.Bean2;

import java.io.IOException;

/**
 * Created by kkulagin on 4/17/2015.
 */

public class DeserializerTwo extends JsonDeserializer<Bean2> {

  @Override
  public Bean2 deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    Bean2 bean2 = new Bean2();
    bean2.setBean2String("DeserializerTwo");
    return bean2;
  }
}

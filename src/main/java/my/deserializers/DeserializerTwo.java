package my.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import my.beans.Bean1;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by kkulagin on 4/17/2015.
 */

public class DeserializerTwo extends JsonDeserializer<Bean1> {
  @Override
  public Bean1 deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    Bean1 bean1 = new Bean1();
    bean1.setForViewOne("DeserializerTwo");
    return bean1;
  }
}

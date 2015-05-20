package my.json.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.json.Bean1;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by kkulagin on 5/19/2015.
 */
public class Json2JavaTest {

  @Test
  public void testDeserialize() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    Bean1 fullBean = mapper.readValue(new File("fullBean1.json"), Bean1.class);
    Assert.assertEquals(fullBean.getS1(), "asdf");

    Bean1 noStringBean1 = mapper.readValue(new File("noStringBean1.json"), Bean1.class);
    Assert.assertNull(noStringBean1.getS1());


    JsonNode jsonNode = mapper.readTree(new File("fullBean1.json"));
  }
}

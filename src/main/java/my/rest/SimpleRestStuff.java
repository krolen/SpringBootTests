package my.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import my.beans.Bean1;
import my.beans.Bean2;
import my.deserializers.DeserializerOne;
import my.deserializers.DeserializerTwo;
import my.serializers.SerializerOne;
import my.serializers.SerializerTwo;
import my.views.Views;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kkulagin
 * @since 16.04.2015
 */
@RestController
@RequestMapping(value = "/simple", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SimpleRestStuff {

  @RequestMapping(value = "/beans", method = RequestMethod.GET, consumes = {MediaType.ALL_VALUE})
  @JsonView(Views.OneAndLazy.class)
  public Bean1 getSimpleBean() {

    Bean1 bean1 = new Bean1();
    bean1.setBean2(new Bean2());
    return bean1;
  }

  @RequestMapping(value = "/bean", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE})
  @JsonView(Views.OneAndLazy.class)
  public Bean1 getSimpleBean(@RequestBody Bean1 bean1) {
    return bean1;
  }

  @RequestMapping(value = "/beanNoAnnotation", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE})
  public Bean1 getSimpleBeanNoAnnotation(Bean1 bean1) {
    return bean1;
  }

  @RequestMapping(value = "/customBean", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE})
  public @JsonSerialize(using = SerializerOne.class) Bean1 getSimpleBeanWithCustomJson(@JsonDeserialize(using = DeserializerOne.class) Bean1 bean1) {
    return bean1;
  }

  @RequestMapping(value = "/customBean2", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE})
  public @JsonSerialize(using = SerializerTwo.class) Bean2 getSimpleBeanWithCustomJson(@JsonDeserialize(using = DeserializerTwo.class) Bean2 bean2) {
    return bean2;
  }
}

package my.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonSerializer;
import my.beans.Bean1;
import my.beans.Bean2;
import my.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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
    Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
    return null;
  }
}

package my.rest;

import com.fasterxml.jackson.annotation.JsonView;
import my.beans.Bean1;
import my.beans.Bean2;
import my.views.Views;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author kkulagin
 * @since 16.04.2015
 */
@RestController
@RequestMapping(value = "/simple", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SimpleRestStuff {

  @RequestMapping(value = "/bean", method = RequestMethod.GET, consumes = {MediaType.ALL_VALUE})
  @JsonView(Views.OneAndLazy.class)
  public Bean1 getSimpleBean() {
    Bean1 bean1 = new Bean1();
    bean1.setBean2(new Bean2());
    return bean1;
  }

//  @RequestMapping(value = "/bean", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE})
//  @JsonView(Views.OneAndLazy.class)
//  public Bean1 getSimpleBean(@JsonView(Views.ViewOne.class) @RequestBody Bean1 bean1) {
//    Bean1 bean1 = new Bean1();
//    bean1.setBean2(new Bean2());
//    return bean1;
//  }
}

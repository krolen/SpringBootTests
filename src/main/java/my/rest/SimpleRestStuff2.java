package my.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonSerializer;
import my.beans.Bean1;
import my.beans.Bean2;
import my.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kkulagin
 * @since 16.04.2015
 */
@RestController
@RequestMapping(value = "/simple2", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SimpleRestStuff2 {



  @RequestMapping(value = "/beans", method = RequestMethod.GET, consumes = {MediaType.ALL_VALUE})
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

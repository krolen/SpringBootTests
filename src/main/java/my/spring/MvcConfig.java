package my.spring;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by kkulagin on 4/19/15.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

  @Autowired
  private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

  @PostConstruct
  public void init() {
    List<HandlerMethodArgumentResolver> argumentResolvers = Lists.newArrayList(requestMappingHandlerAdapter.getArgumentResolvers());
    MyHandlerMethodArgumentResolver myHandlerMethodArgumentResolver = new MyHandlerMethodArgumentResolver();
    argumentResolvers.add(0, myHandlerMethodArgumentResolver);
    requestMappingHandlerAdapter.setArgumentResolvers(argumentResolvers);
    List<HandlerMethodReturnValueHandler> returnValueHandlers = Lists.newArrayList(requestMappingHandlerAdapter.getReturnValueHandlers());
    returnValueHandlers.add(0, myHandlerMethodArgumentResolver);
    requestMappingHandlerAdapter.setReturnValueHandlers(returnValueHandlers);
  }


}


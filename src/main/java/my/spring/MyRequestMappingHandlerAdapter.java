package my.spring;

import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by kkulagin on 4/19/15.
 */
//@Component
public class MyRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {

  @Override
  public void afterPropertiesSet() {
    super.afterPropertiesSet();
    List<HandlerMethodArgumentResolver> resolvers = getArgumentResolvers().stream().
        map(new Function<HandlerMethodArgumentResolver, HandlerMethodArgumentResolver>() {
          @Override
          public HandlerMethodArgumentResolver apply(HandlerMethodArgumentResolver handlerMethodArgumentResolver) {
            if (handlerMethodArgumentResolver.getClass().isAssignableFrom(RequestResponseBodyMethodProcessor.class)) {
              return new MyRequestResponseBodyMethodProcessor(getMessageConverters());
            }
            return handlerMethodArgumentResolver;
          }
        }).collect(Collectors.toList());

    setArgumentResolvers(resolvers);

  }

}

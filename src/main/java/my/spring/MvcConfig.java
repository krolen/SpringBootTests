package my.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;

/**
 * Created by kkulagin on 4/19/15.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        System.out.println("argumentResolvers = " + argumentResolvers.size());
        argumentResolvers.add(new MyRequestResponseBodyMethodProcessor());
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // equivalent to <mvc:message-converters>
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        RequestResponseBodyMethodProcessor processor = event.getApplicationContext().getBean(RequestResponseBodyMethodProcessor.class);

    }
}


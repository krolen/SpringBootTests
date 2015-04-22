package my;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import my.beans.Bean1;
import my.deserializers.DeserializerOne;
import my.deserializers.DeserializerTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    @Bean
    public JsonSerializer myFirstSerializer() {
        return new ToStringSerializer();
    }

    @Bean
    public JsonDeserializer<Bean1> deserializerOne() {
        return new DeserializerOne();
    }

    @Bean
    public JsonDeserializer<Bean1> deserializerTwo() {
        return new DeserializerTwo();
    }

//    @Autowired
//    private RequestMappingHandlerAdapter adapter;

//    @PostConstruct
//    public void prioritizeCustomArgumentMethodHandlers () {
//        List<HandlerMethodArgumentResolver> argumentResolvers =
//            new ArrayList<>(adapter.getArgumentResolvers ());
//        List<HandlerMethodArgumentResolver> customResolvers =
//            adapter.getCustomArgumentResolvers ();
//        argumentResolvers.removeAll (customResolvers);
//        argumentResolvers.addAll (0, customResolvers);
//        adapter.setArgumentResolvers (argumentResolvers);
//    }



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

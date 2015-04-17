package my;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import my.beans.Bean1;
import my.deserializers.DeserializerOne;
import my.deserializers.DeserializerTwo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

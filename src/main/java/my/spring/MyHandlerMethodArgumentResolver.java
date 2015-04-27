package my.spring;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kkulagin on 4/20/2015.
 */
public class MyHandlerMethodArgumentResolver extends RequestResponseBodyMethodProcessor {

  private final Map<MethodParameter, HttpMessageConverter> convertersCache = new ConcurrentHashMap<>(256);

  public MyHandlerMethodArgumentResolver() {
    super(Lists.newArrayList(new ThreadLocalConverter()));
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(JsonDeserialize.class);
  }

  @Override
  public boolean supportsReturnType(MethodParameter returnType) {
    return returnType.getMethodAnnotation(JsonSerialize.class) != null;
  }

  @Override
  protected <T> Object readWithMessageConverters(NativeWebRequest webRequest, MethodParameter methodParam, Type paramType) throws IOException, HttpMediaTypeNotSupportedException {
    HttpMessageConverter converter = convertersCache.get(methodParam);
    if (converter == null) {
      JsonDeserialize annotation = methodParam.getParameterAnnotation(JsonDeserialize.class);
      Class<? extends JsonDeserializer<?>> deserializerClass = annotation.using();
      converter = createParamConverter(deserializerClass, methodParam.getParameterType());
      convertersCache.put(methodParam, converter);
    }
    try {
      ((ThreadLocalConverter) messageConverters.get(0)).set(converter);
      return super.readWithMessageConverters(webRequest, methodParam, paramType);
    } finally {
      ((ThreadLocalConverter) messageConverters.get(0)).set(null);
    }
  }

  @Override
  protected <T> void writeWithMessageConverters(T returnValue, MethodParameter returnType, NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException {
    HttpMessageConverter converter = convertersCache.get(returnType);
    if (converter == null) {
      JsonSerialize annotation = returnType.getMethodAnnotation(JsonSerialize.class);
      Class<? extends JsonSerializer<?>> serializer = annotation.using();
      converter = createReturnConverter(serializer, returnType.getParameterType());
      convertersCache.put(returnType, converter);
    }
    try {
      ((ThreadLocalConverter) messageConverters.get(0)).set(converter);
      super.writeWithMessageConverters(returnValue, returnType, webRequest);
    } finally {
      ((ThreadLocalConverter) messageConverters.get(0)).set(null);
    }
  }

  private HttpMessageConverter createParamConverter(Class<? extends JsonDeserializer<?>> deserializer, Class<?> parameterType) {
    Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
    if(deserializer != null) {
      try {
        JsonDeserializer<?> instance = deserializer.newInstance();
        builder.deserializerByType(parameterType, instance);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return new MappingJackson2HttpMessageConverter(builder.build());
  }

  private HttpMessageConverter createReturnConverter(Class<? extends JsonSerializer<?>> serializer, Class<?> parameterType) {
    Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
    if(serializer != null) {
      try {
        JsonSerializer<?> instance = serializer.newInstance();
        builder.serializerByType(parameterType, instance);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return new MappingJackson2HttpMessageConverter(builder.build());
  }

}

package my.spring;

import org.springframework.http.*;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkulagin on 4/21/2015.
 */
public class HttpMessageConverterComposite<T> extends AbstractHttpMessageConverter<T> {

  private List<HttpMessageConverter<?>> converters = new ArrayList<>();


  public void addConverter(int place, HttpMessageConverter<?> converter) {
    converters.add(place, converter);
    List<MediaType> existingTypes = new ArrayList<>(getSupportedMediaTypes());
    List<MediaType> supportedMediaTypes = converter.getSupportedMediaTypes();
    supportedMediaTypes.stream().filter((type) -> !existingTypes.contains(type)).map(existingTypes::add);
    setSupportedMediaTypes(existingTypes);
  }

  public void addConverter(HttpMessageConverter converter) {
    addConverter(converters.size(), converter);
  }

  private HttpMessageConverter getConverterForRead(Class<?> clazz, MediaType mediaType) {
    return converters.stream().filter((converter) -> converter.canRead(clazz, mediaType)).findFirst().orElse(null);
  }

  private HttpMessageConverter getConverterForWrite(Class<?> clazz, MediaType mediaType) {
    return converters.stream().filter((converter) -> converter.canWrite(clazz, mediaType)).findFirst().orElse(null);
  }

  @Override
  protected boolean supports(Class<?> clazz) {
    return false;
  }

  @Override
  protected T readInternal(Class<? extends T> clazz, HttpInputMessage message) throws IOException, HttpMessageNotReadableException {
    MediaType contentType = getMediaType(message);

    return (T) getConverterForRead(clazz, contentType).read(clazz, message);
  }

  @Override
  protected void writeInternal(T t, HttpOutputMessage message) throws IOException, HttpMessageNotWritableException {
    MediaType contentType = getMediaType(message);
    getConverterForWrite(t.getClass(), contentType).write(t, contentType, message);
  }

  private MediaType getMediaType(HttpMessage inputMessage) {
    MediaType contentType = null;
    try {
      contentType = inputMessage.getHeaders().getContentType();
    } catch (InvalidMediaTypeException ignored) {
    }
    return contentType;
  }


}

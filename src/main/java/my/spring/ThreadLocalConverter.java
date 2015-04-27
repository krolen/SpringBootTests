package my.spring;

import com.google.common.collect.ImmutableList;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.List;

/**
 * Created by kkulagin on 4/22/2015.
 */
public class ThreadLocalConverter implements HttpMessageConverter {

  private static final HttpMessageConverter FAKE_CONVERTER = new FakeMessageConverter();

  private final ThreadLocal<HttpMessageConverter> converters = new ThreadLocal<HttpMessageConverter>() {
    @Override
    protected HttpMessageConverter initialValue() {
      return FAKE_CONVERTER;
    }
  };


  public void set(HttpMessageConverter converter) {
    converters.set(converter);
  }

  @Override
  public boolean canRead(Class clazz, MediaType mediaType) {
    return converters.get().canRead(clazz, mediaType);
  }

  @Override
  public boolean canWrite(Class clazz, MediaType mediaType) {
    return converters.get().canWrite(clazz, mediaType);
  }

  @Override
  public List<MediaType> getSupportedMediaTypes() {
    return converters.get().getSupportedMediaTypes();
  }

  @Override
  public Object read(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
    return converters.get().read(clazz, inputMessage);
  }

  @Override
  public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    converters.get().write(o, contentType, outputMessage);
  }

  private static class FakeMessageConverter implements HttpMessageConverter {
    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
      return false;
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
      return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
      return ImmutableList.of();
    }

    @Override
    public Object read(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
  }
}

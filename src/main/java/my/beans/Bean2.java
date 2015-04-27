package my.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import my.views.Views;

/**
 * @author kkulagin
 * @since 16.04.2015
 */
public class Bean2 {
//  @JsonView(value = Views.LazyEntity.class)
  @JsonIgnore
  private int id;
  private String bean2String;

  public Bean2() {
  }

  public Bean2(String bean2String) {
    this.bean2String = bean2String;
  }

  public String getBean2String() {
    return bean2String;
  }

  public void setBean2String(String bean2String) {
    this.bean2String = bean2String;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}

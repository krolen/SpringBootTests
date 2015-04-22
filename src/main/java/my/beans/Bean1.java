package my.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import my.views.Views;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkulagin
 * @since 16.04.2015
 */
public class Bean1 {

  @JsonIgnore
  private int id;

  private String bean1String;
//  @JsonView(value = Views.ViewOne.class)
  private String forViewOne;

  @JsonView(value = Views.LazyEntity.class)
  private Bean2 bean2;

  @JsonView(value = Views.LazyEntity.class)
  private List<Bean2> beans2 = new ArrayList<>();

  public Bean1() {
    beans2.add(new Bean2("list bean"));
  }

  public String getBean1String() {
    return bean1String;
  }

  public void setBean1String(String bean1String) {
    this.bean1String = bean1String;
  }

  public Bean2 getBean2() {
    return bean2;
  }

  public void setBean2(Bean2 bean2) {
    this.bean2 = bean2;
  }

  public String getForViewOne() {
    return forViewOne;
  }

  public void setForViewOne(String forViewOne) {
    this.forViewOne = forViewOne;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}

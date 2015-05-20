package my.json;

/**
 * Created by kkulagin on 5/19/2015.
 */
public class Bean1 {

  private String s1;
  private int i1;
  private Bean2 bean2;

  public String getS1() {
    return s1;
  }

  public void setS1(String s1) {
    System.out.println("setting " + s1);
    this.s1 = s1;
  }

  public int getI1() {
    return i1;
  }

  public void setI1(int i1) {
    this.i1 = i1;
  }

  public Bean2 getBean2() {
    return bean2;
  }

  public void setBean2(Bean2 bean2) {
    this.bean2 = bean2;
  }
}

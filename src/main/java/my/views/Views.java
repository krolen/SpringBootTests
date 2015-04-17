package my.views;

/**
 * @author kkulagin
 * @since 16.04.2015
 */
public class Views {

  public interface ViewOne {

  }

  public interface LazyEntity {

  }

  public interface OneAndLazy extends LazyEntity, ViewOne {

  }
}

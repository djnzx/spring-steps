package app.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCustomBean implements DisposableBean, InitializingBean {

  public MyCustomBean() {
    System.out.println("MyCustomBean - constructor");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("MyCustomBean - destroy");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("MyCustomBean - afterPropertiesSet");
  }
}

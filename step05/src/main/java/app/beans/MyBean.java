package app.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyBean implements DisposableBean, InitializingBean {

  public MyBean() {
    log.info("::MyBean::constructor");
  }

  @Override
  public void destroy() throws Exception {
    log.info("::MyBean::destroy()");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("::MyBean::afterPropertiesSet()");
  }
}

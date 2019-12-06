package app.beans;

import app.service.UserInterface;
import app.service.UserServiceV1;
import app.service.UserServiceV2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserServiceBeans {

  @Bean
  public UserInterface US_V1() {
    return new UserServiceV1();
  }

  @Bean
  public UserInterface US_V2() {
    return new UserServiceV2();
  }
}

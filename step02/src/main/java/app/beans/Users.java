package app.beans;

import app.service.UserInterface;
import app.service.UserService1;
import app.service.UserService2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Users {

  @Bean
  public UserInterface us1() {
    return new UserService1();
  }

  @Bean
  public UserInterface us2() {
    return new UserService2();
  }
}

package app.service;

import app.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Primary
@Service
public class UserServiceV1 implements UserInterface {

  private final List<Person> content = new LinkedList<>();

  @Bean
  public UserInterface UserService_V1() {
    return new UserServiceV1();
  }

  public UserServiceV1() {
    content.add(new Person("Jim"));
    content.add(new Person("Jack"));
    content.add(new Person("John"));
  }

  @Override
  public List<Person> get() {
    return content;
  }

  @Override
  public void add(String name) {
    content.add(new Person(name));
  }

}

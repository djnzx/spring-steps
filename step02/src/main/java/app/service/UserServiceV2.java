package app.service;

import app.entity.Person;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceV2 implements UserInterface {

  private final List<Person> content = new LinkedList<>();

  public UserServiceV2() {
    content.add(new Person("Jim2"));
    content.add(new Person("Jack2"));
    content.add(new Person("John2"));
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

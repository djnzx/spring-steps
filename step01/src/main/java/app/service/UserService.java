package app.service;

import app.entity.Person;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {

  private final List<Person> content = new LinkedList<>();

  public UserService() {
    content.add(new Person("Jim"));
    content.add(new Person("Jack"));
    content.add(new Person("John"));
  }

  public List<Person> getAll() {
    return content;
  }

  public void addPerson(String name) {
    content.add(new Person(name));
  }

}

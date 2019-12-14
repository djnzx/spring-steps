package app.controller;

import app.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

  @GetMapping("/users")
  public List<Person> btdf () {
    return Arrays.asList(
        new Person("Jim"),
        new Person("John"),
        new Person("Mario"));
  }

}

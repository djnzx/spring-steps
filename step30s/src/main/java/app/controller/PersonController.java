package app.controller;

import app.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

  /**
   * http://localhost:8081/person
   */
  @GetMapping
  public Person handle() {
    return new Person(33, "Jim");
  }
}

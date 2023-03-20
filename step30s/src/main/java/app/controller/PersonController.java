package app.controller;

import app.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  @GetMapping("person")
  public Person handle(@RequestParam("id") Integer id) {
    return new Person(id, "Jim");
  }
}

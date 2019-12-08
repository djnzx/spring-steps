package app.controller;

import app.entity.Person;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/p")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping("")
  public Iterable<Person> response_get_all() {
    return personService.get_all();
  }

  @GetMapping("/{id}")
  public Optional<Person> response_get(@PathVariable("id") long id) {
    Optional<Person> one = personService.get_one(id);
    one.ifPresent(p -> System.out.println(p.getResponsibilities()));
    return one;
  }

  @PostMapping
  public Person response_post(@RequestBody Person p) {
    System.out.println(p);
    personService.create_one(p);
    return p;
  }

  @PostMapping("/cr")
  public List<Person> create_initial() {
    return personService.create_initial();
  }

  @PostMapping("/cr1")
  public Person create_nested() {
    return personService.create_nested();
  }

}

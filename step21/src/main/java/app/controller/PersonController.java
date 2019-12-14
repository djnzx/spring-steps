package app.controller;

import app.entity.Person;
import app.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  public Iterable<Person> response_get_all() {
    return personService.get_all();
  }

  @GetMapping("/{id}")
  public Optional<Person> response_get(@PathVariable("id") long id) {
    return personService.get_one(id);
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

}

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
  public Iterable<Person> handle_get_all() {
    return personService.get_all();
  }

  @GetMapping("/{id}")
  public Optional<Person> handle_get(@PathVariable("id") long id) {
    return personService.get_one(id);
  }

  @DeleteMapping("/{id}")
  public void handle_delete_one(@PathVariable("id") long id) {
    personService.del_one(id);
  }

  @PutMapping
  public Person handle_post(@RequestBody Person p) {
    return personService.create_one(p);
  }

  @PostMapping("/cr")
  public List<Person> create_initial() {
    return personService.create_initial();
  }

}

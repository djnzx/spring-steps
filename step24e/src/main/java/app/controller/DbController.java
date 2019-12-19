package app.controller;

import app.entity.Person;
import app.entity.Phone;
import app.service.PersonService;
import app.service.PhoneService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbController {

  private final PersonService personService;
  private final PhoneService phoneService;

  public DbController(PersonService personService, PhoneService phoneService) {
    this.personService = personService;
    this.phoneService = phoneService;
  }

  // Phone WITHOUT nested Persons
  @GetMapping("/ph/{id}")
  @JsonView(Views.NoNested.class)
  public Phone handle_phone1(@PathVariable long id) {
    return phoneService.fetch(id);
  }

  // Person WITHOUT nested phones
  @GetMapping("/p/{id}")
  @JsonView(Views.NoNested.class)
  public Person handle_person1(@PathVariable long id) {
    return personService.fetch(id);
  }

  // Phone WITH nested Persons
  @GetMapping("/phn/{id}")
  @JsonView(Views.AllowPhoneToPerson.class)
  public Phone handle_phone2(@PathVariable long id) {
    return phoneService.fetch(id);
  }

  // Person WITH nested phones
  @GetMapping("/pn/{id}")
  @JsonView(Views.AllowPersonToPhone.class)
  public Person handle_person2(@PathVariable long id) {
    return personService.fetch(id);
  }

}

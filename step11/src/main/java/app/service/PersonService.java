package app.service;

import app.entity.Person;
import app.ex.OrderNotFoundException;
import app.ex.PersonNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  public Person get1() {
    return new Person(1,"Jim");
  }

  public Person get2() {
    throw new PersonNotFoundException();
  }

  public Person get3() {
    throw new OrderNotFoundException();
  }

}

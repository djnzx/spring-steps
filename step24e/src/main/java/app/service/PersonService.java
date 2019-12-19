package app.service;

import app.entity.Person;
import app.repo.PersonRepo;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  private final PersonRepo personRepo;

  public PersonService(PersonRepo personRepo) {
    this.personRepo = personRepo;
  }

  public Person fetch(long id) {
    return personRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
  }

}

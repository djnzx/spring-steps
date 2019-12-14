package app.service;

import app.entity.Person;
import app.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonService {

  private final PersonRepository personRepository;

  public PersonService(PersonRepository repository) {
    this.personRepository = repository;
  }

  public Optional<Person> get_one(long pk) {
    return personRepository.findById(pk);
  }

  public Iterable<Person> get_all() {
    return personRepository.findAll();
  }

  public Person create_one(Person p) {
    personRepository.save(p);
    return p;
  }

  public List<Person> create_initial() {
    List<Person> ps = Stream.of(
        "Jim",
        "John"
    ).map(Person::new)
        .collect(Collectors.toList());
    personRepository.saveAll(ps);
    return ps;
  }
}

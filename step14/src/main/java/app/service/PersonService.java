package app.service;

import app.entity.Person;
import app.entity.Responsibility;
import app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonService {

  private final PersonRepository personRepository;

  @Autowired
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
        "Jim", "John"
    ).map(n -> new Person(0, n, null))
        .collect(Collectors.toList());
    personRepository.saveAll(ps);
    return ps;
  }

  public Person create_nested() {
    Person p = new Person(0, "Alex", null);
    p.setResponsibilities(Arrays.asList(new Responsibility(1, "Cool")));
    personRepository.save(p);
    return p;
  }
}

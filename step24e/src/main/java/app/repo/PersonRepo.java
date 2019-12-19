package app.repo;

import app.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends CrudRepository<Person, Long> {
  Optional<Person> findByNameIgnoreCase(String name);
}

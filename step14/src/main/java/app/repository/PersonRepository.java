package app.repository;

import app.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
//  @Query("Person p where p.name='Jim'")
//  List<Person> getAllSmartPerson();
}

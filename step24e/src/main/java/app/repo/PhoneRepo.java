package app.repo;

import app.entity.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PhoneRepo extends CrudRepository<Phone, Long> {
  Collection<Phone> findAllByNumberStartsWith(String front_part);
}

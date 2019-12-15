package app.security.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DbUserRepo extends CrudRepository<DbUser, Integer> {
  Optional<DbUser> findByUsername(String username);
}

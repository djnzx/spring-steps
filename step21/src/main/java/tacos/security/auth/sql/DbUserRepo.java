package tacos.security.auth.sql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbUserRepo extends CrudRepository<DbUser, Integer> {
}

package app.repo;

import app.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDbRepository extends CrudRepository<Message, Integer> {
  Iterable<Message> findMessagesByContentContaining(String name);
  Iterable<Message> findMessagesByUser1EqualsAndUser2Equals(int user1, int user2);
}

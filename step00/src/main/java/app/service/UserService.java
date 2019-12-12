package app.service;

import app.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

  private final Map<Integer, User> data = new HashMap<>();

  public UserService() {
    data.put(1, new User("Jim"));
    data.put(2, new User("John"));
    data.put(3, new User("Jack"));
  }

  public Collection<User> getAll() {
    return data.values();
  }

  public User getOne(int id) {
    return data.get(id);
  }

  public User createOne(String name) {
    User user = new User(name);
    data.put(data.size() + 1, user);
    return user;
  }
}

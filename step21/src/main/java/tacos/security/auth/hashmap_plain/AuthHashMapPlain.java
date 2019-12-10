package tacos.security.auth.hashmap_plain;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import tacos.security.auth.Authentication;
import tacos.security.Roles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class AuthHashMapPlain implements Authentication {

  private final Map<String, String> storage = new HashMap<>();

  public AuthHashMapPlain() {
    storage.put("alex","123");
    storage.put("jim","456");
    storage.put("admin","admin");
  }

  @Override
  public List<UserDetails> usersDetails() {
    return storage.entrySet().stream().map(this::mapper).collect(Collectors.toList());
  }

  /**
   * mapper function to map
   * login and password from HashMap
   * to SpringSecurity UserDetails entity
   */
  private UserDetails mapper(Map.Entry<String, String> entry) {
    return User
        .withDefaultPasswordEncoder() // we encode password here with `default` method
        .username(entry.getKey())
        .password(entry.getValue())
        .roles(Roles.a_user())        // all of our users will have the role `USER`
        .build();
  }

}

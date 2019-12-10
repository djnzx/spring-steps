package tacos.security.auth.hashmap_encoded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.security.auth.Authentication;
import tacos.security.Roles;

import java.util.*;
import java.util.stream.Collectors;

//@Primary
@Configuration
public class AuthHashMapEncoded implements Authentication {

  private final Map<String, String> storage = new HashMap<>();
  private final PasswordEncoder enc;

  @Autowired
  public AuthHashMapEncoded(PasswordEncoder enc) {
    this.enc = enc;
    storage.put("john", "123");
    storage.put("jack", "456");
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
        .withUsername(entry.getKey())  // user name
        .password(entry.getValue())    // password plain
        .passwordEncoder(enc::encode)  // function to encode
        .roles(Roles.a_user())
        .build();
  }

}

package tacos.security.auth.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import tacos.security.auth.Authentication;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Primary
@Configuration
public class AuthDatabaseEncrypted implements Authentication {

  private final DbUserRepo repo;

  @Autowired
  public AuthDatabaseEncrypted(DbUserRepo repo) {
    this.repo = repo;
  }

  @Override
  public Collection<UserDetails> usersDetails() {
    return StreamSupport.stream(repo.findAll().spliterator(), false)
        .map(this::mapper).collect(Collectors.toList());
  }

  /**
   * mapper function to map
   * login and password from HashMap
   * to SpringSecurity UserDetails entity
   */
  private UserDetails mapper(DbUser entry) {
    return User
        .withUsername(entry.getName()) // read user from DB
        .password(entry.getPassword()) // read password from DB encoded
        .roles(entry.getRoles())       // read roles
        .build();
  }

}

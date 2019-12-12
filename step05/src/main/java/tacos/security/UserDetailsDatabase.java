package tacos.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Primary
@Configuration
public class UserDetailsDatabase implements Auth {

  private final DbUserRepo repo;

  public UserDetailsDatabase(DbUserRepo repo) {
    this.repo = repo;
  }

  private UserDetails mapper(DbUser user) {
    return User
        .withUsername(user.getUsername())
        .password(user.getPassword())
//        .passwordEncoder(enc::encode)
        .roles(user.getRoles())
        .build();
  }

  @Override
  public UserDetailsService content() {
    Set<UserDetails> data = StreamSupport.stream(repo.findAll().spliterator(), false)
        .map(this::mapper)
        .collect(Collectors.toSet());
    return new InMemoryUserDetailsManager(data);
  }

}

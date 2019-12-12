package app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Configuration
public class UserDetailsJPA {

  private final DbUserRepo dbUserRepo;

  public UserDetailsJPA(DbUserRepo dbUserRepo) {
    this.dbUserRepo = dbUserRepo;
  }

  private UserDetails mapper(DbUser dbUser) {
    return User
        .withUsername(dbUser.getUsername())
        .password(dbUser.getPassword())
        // we don't need the password encoder, because
        // passwords in the database already stored in encoded format
//        .passwordEncoder(enc::encode)
        .roles(dbUser.getRoles()) // all of the users have only one role: "USER"
        .build();
  }

  @Bean
  public UserDetailsService udsHashMapEncoded() {
    Set<UserDetails> data = StreamSupport.stream(dbUserRepo.findAll().spliterator(), false)
        .map(this::mapper)
        .collect(Collectors.toSet());
    return new InMemoryUserDetailsManager(data);
  }
}

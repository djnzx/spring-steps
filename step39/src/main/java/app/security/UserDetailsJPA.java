package app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Configuration
public class UserDetailsJPA {

  private final DbUserRepo dbUserRepo;

  public UserDetailsJPA(DbUserRepo dbUserRepo) {
    this.dbUserRepo = dbUserRepo;
  }

  public static UserDetails mapper(DbUser dbUser) {
    return User
        .withUsername(dbUser.getUsername())
        .password(dbUser.getPassword())
        // we don't need the password encoder, because
        // passwords in the database already stored in encoded format
//        .passwordEncoder(enc::encode)
        .roles(dbUser.getRoles()) // all of the users have only one role: "USER"
//        .authorities()
        .build();
  }

//  @Bean
  public UserDetailsService udsHashMapJPA() {
    Set<UserDetails> data = StreamSupport.stream(dbUserRepo.findAll().spliterator(), false)
        .map(UserDetailsJPA::mapper)
        .collect(Collectors.toSet());
    return new InMemoryUserDetailsManager(data);
  }
}

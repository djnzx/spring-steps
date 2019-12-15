package app.security.ud;

import app.security.jpa.DbUser;
import app.security.jpa.DbUserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j2
@Configuration
public class UserDetailsServiceJPA implements UserDetailsService {

  private final DbUserRepo dbUserRepo;

  public UserDetailsServiceJPA(DbUserRepo dbUserRepo) {
    this.dbUserRepo = dbUserRepo;
  }

  public static UserDetails mapper(DbUser dbUser) {
    return User
        .withUsername(dbUser.getUsername())
        .password(dbUser.getPassword())
        .roles(dbUser.getRoles())
        .build();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info(String.format(">>>>>>> UserDetails.loadUserByUsername:%s", username));
    return dbUserRepo.findByUsername(username).map(UserDetailsServiceJPA::mapper)
        .orElseThrow(() -> new UsernameNotFoundException(
            String.format("User `%s` not found", username)));
  }
}

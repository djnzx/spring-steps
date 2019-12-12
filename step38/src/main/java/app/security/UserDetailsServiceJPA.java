package app.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
@Configuration
public class UserDetailsServiceJPA implements UserDetailsService {

  private final DbUserRepo dbUserRepo;

  public UserDetailsServiceJPA(DbUserRepo dbUserRepo) {
    this.dbUserRepo = dbUserRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info(String.format(">>>>>>> UserDetails.loadUserByUsername:%s", username));
    return dbUserRepo.findByUsername(username).map(UserDetailsJPA::mapper)
        .orElseThrow(() -> new UsernameNotFoundException(
            String.format("User `%s` not found", username)));
  }
}

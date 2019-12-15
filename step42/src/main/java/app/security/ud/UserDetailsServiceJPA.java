package app.security.ud;

import app.security.jpa.DbUser;
import app.security.jpa.DbUserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
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
    return new MyUserDetails(
        dbUser.getId(),
        dbUser.getUsername(),
        dbUser.getPassword(),
        dbUser.getRoles()
    );
  }

  /**
   * we use this method in standard Spring Security authorization mechanism
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info(String.format(">>>>>>> UserDetails.loadUserByUsername:%s", username));
    return dbUserRepo.findByUsername(username)
        .map(UserDetailsServiceJPA::mapper)
        .orElseThrow(() -> new UsernameNotFoundException(
            String.format("User `%s` not found", username)));
  }

  /**
   * we will use this method in JWT authorization part
   * @throws UsernameNotFoundException
   */
  public UserDetails loadUserById(long userid) throws UsernameNotFoundException {
    log.info(String.format(">>>>>>> UserDetails.loadUserById:%d", userid));
    return dbUserRepo.findById(userid)
        .map(UserDetailsServiceJPA::mapper)
        .orElseThrow(() -> new UsernameNotFoundException(
            String.format("User with id:%d` not found", userid)));
  }

}

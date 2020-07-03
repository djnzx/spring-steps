package app.conf.sec;

import app.entity.db.XUser;
import app.entity.sec.XUserDetails;
import app.repo.XUserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j2
@Configuration
public class UserDetailsServiceJPA implements UserDetailsService {

  private final XUserRepo repo;

  public UserDetailsServiceJPA(XUserRepo repo) {
    this.repo = repo;
  }

  public static UserDetails mapper_to_standard_ud(XUser xUser) {
    return User
        .withUsername(xUser.getUsername())
        .password(xUser.getPassword())
        .roles()
        .build();
  }

  public static UserDetails mapper_to_my_ud(XUser xUser) {
    return new XUserDetails(
        xUser.getId(),
        xUser.getUsername(),
        xUser.getPassword(),
        xUser.getRoles()
    );
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repo.findByUsername(username)
//        .map(UserDetailsServiceJPA::mapper_to_standard_ud)
        .map(UserDetailsServiceJPA::mapper_to_my_ud)
        .orElseThrow(() -> {
          String msg = String.format("User `%s` is not found in the database", username);
          log.warn(msg);
          return new UsernameNotFoundException(msg);
        });
  }

}

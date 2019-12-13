package tacos.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

//@Primary
@Configuration
public class UserDetailsStatic implements Auth {

  @Override
  public UserDetailsService content() {
    return new InMemoryUserDetailsManager(Arrays.asList(
        User.withDefaultPasswordEncoder()
            .username("jim")
            .password("123")
            .roles("USER")
            .build()
    ));
  }
}

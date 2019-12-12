package app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  @Override
  public UserDetailsService userDetailsServiceBean() throws Exception {
    return new InMemoryUserDetailsManager(Arrays.asList(
        User.withDefaultPasswordEncoder().username("jim").password("123").roles().build(),
        User.withDefaultPasswordEncoder().username("john").password("234").roles().build()
    ));
  }

}

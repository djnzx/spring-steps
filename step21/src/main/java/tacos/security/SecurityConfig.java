package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import tacos.security.auth.Authentication;
import tacos.security.auth.sql.DbUser;
import tacos.security.auth.sql.DbUserRepo;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  public SecurityConfig(DbUserRepo repo, PasswordEncoder enc) {
    // new user is going to be created here (with password already encoded)
    repo.save(new DbUser("mario", enc.encode("123"), Roles.a_user()));
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf()
//          .csrfTokenRepository()
//          .and()
          .disable()
//        .cors()
//          .and()
        .authorizeRequests()
          .antMatchers("/login")
            .permitAll()
          .anyRequest()
            .authenticated()
          .and()
        .formLogin()
//          .loginPage("/login7") // you can write your own
//          .usernameParameter("...")
//          .passwordParameter("...")
//          .successForwardUrl("...")
//          .failureForwardUrl("...")
//          .failureHandler(null)
          .permitAll()
          .and()
        .logout()
          .invalidateHttpSession(true)
          .clearAuthentication(true)
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .logoutSuccessUrl("/logout-success")
          .permitAll();
  }

  /**
   * Naive plain implementation
   */
//  @Bean
  public UserDetailsService userDetailsServicePlan() {
    User.UserBuilder builder = User.withDefaultPasswordEncoder();
    return new InMemoryUserDetailsManager(Arrays.asList(
        builder.username("jim").password("123").roles("USER").build(),
        builder.username("john").password("456").roles("ADMIN").build()
    ));
  }

  /**
   * Our custom implementation
   * if you want to load them from DB, just implement it by your self
   */
  @Bean
  public UserDetailsService userDetailsServiceMy(Authentication auth) {
    return new InMemoryUserDetailsManager(auth.usersDetails());
  }
}

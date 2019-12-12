package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class MySecurity extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf()
//          .csrfTokenRepository()
//          .and()
        .disable()
        .cors()
        .and()
        .authorizeRequests()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/", "/home", "/about", "/resources/**").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/user/**").hasRole("USER")
        .antMatchers(HttpMethod.GET, "/api").permitAll()
        .antMatchers(HttpMethod.POST, "/api").hasAnyAuthority("USER", "ADMIN")
        .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
        //////////////
        .antMatchers("/login").permitAll()
        //////////////
        .anyRequest().authenticated();
        //////////////

//        .and()
      http
        .formLogin()
//        .loginPage("/login7") // you can write your own
//          .usernameParameter("...")
//          .passwordParameter("...")
//          .successForwardUrl("...")
//          .failureForwardUrl("...")
//          .failureHandler(null)
        .permitAll();

//      http
//          .logout()
//          .invalidateHttpSession(true)
//          .clearAuthentication(true)
//          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//          .logoutSuccessUrl("/logout-success")
//          .permitAll();
    http
        .headers().frameOptions().disable();
  }

  public MySecurity(CreateInitialUser initial) {
    initial.create();
  }

  @Bean
  public UserDetailsService users(Auth auth) {
    return auth.content();
  }

}

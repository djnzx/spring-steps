package app.security;

import app.security.jpa.DbUsersInitial;
import app.security.jwt.JwtAuthenticationFilter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Log4j2
@Configuration
@EnableWebSecurity
public class MyAppSecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtAuthenticationFilter jwtFilter;

  public MyAppSecurityConfig(DbUsersInitial initial, JwtAuthenticationFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
    // actually that code must be presented
    // in the user registration service
    // we put it here only because we use H2 in-memory database
    log.info(":::: >> Populating initial users into Database...... >> ::::");
    initial.populate();
    log.info(":::: >> Populating initial users into Database. ...done >> ::::");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // special settings (3 lines) to enable H2 local connection
    // must be removed in production
    // because CSRF must be enabled and implemented in production
    http.csrf().disable();
    http.headers().frameOptions().disable();
    http.authorizeRequests().antMatchers("/h2-console/**").permitAll();

    // switch off JSESSION cookie
    // this staff brakes standard user logging procedure !!!
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // general request rules
    http
        .authorizeRequests()
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/api/register/**", "/api/login/**").permitAll()
        .antMatchers("/guest/**", "/api/guest/**").permitAll()
        .antMatchers("/home/**", "/api/home/**").authenticated()
        .antMatchers("/admin/**", "/api/admin/**").hasRole("ADMIN")
        .antMatchers("/me/**", "/api/me/**").hasRole("USER")
        .antMatchers("/news/**", "/api/news/**").hasAnyRole("ADMIN", "USER")
        // there is no way to configure requests after this line
        .anyRequest().authenticated();

    // add our filter
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    // login handling
//    http.formLogin().permitAll();
  }

  /**
   * we need that only because we inject AuthenticationManager
   * in the AuthService
   */
  @Bean
  public AuthenticationManager myAuthenticationManager() throws Exception {
    return super.authenticationManagerBean();
  }

}

package app.security;

import app.security.jpa.DbUsersInitial;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Log4j2
@Configuration
@EnableWebSecurity
public class MyAppSecurityConfig extends WebSecurityConfigurerAdapter {

  public MyAppSecurityConfig(DbUsersInitial initial) {
    log.info(":::: >> Populating initial users into Database...... >> ::::");
    // actually that code must be presented
    // in the user registration service
    // we put it here only because we use H2 in-memory database
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

    // general request rules
    http
        .authorizeRequests()
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/api/register/**").permitAll()
        .antMatchers("/guest/**", "/api/guest/**").permitAll()
        .antMatchers("/home/**", "/api/home/**").authenticated()
        .antMatchers("/admin/**", "/api/admin/**").hasRole("ADMIN")
        .antMatchers("/me/**", "/api/me/**").hasRole("USER")
        .antMatchers("/news/**", "/api/news/**").hasAnyRole("ADMIN", "USER")
        // there is no way to configure requests after this line
        .anyRequest().authenticated();

    // login handling
    http
        .formLogin().permitAll();
  }
}

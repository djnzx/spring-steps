package app.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

  public MySecurityConfig(DbUsersInitial initial) {
    log.info(":::: >> Inserting initial users into Database...... >> ::::");
    // actually that code must be presented
    // in the user registration service
    // we put it here only because we use H2 in-memory database
    initial.create();
    log.info(":::: >> Inserting initial users into Database. done >> ::::");
  }

}

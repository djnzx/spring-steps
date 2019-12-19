package app.security;

import app.mvc.StaticResourcesConfig;
import app.security.jpa.DbUsersInitial;
import app.security.jwt.JwtAuthenticationFilter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.stream.Stream;

@Log4j2
@Configuration
@EnableWebSecurity
public class MyAppSecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtAuthenticationFilter jwtFilter;

  private final String[] API_AUTH = { "/api/login/**", "/api/register/**" };
  private final String[] API_FREE = { "/api/guest/**" };

  public MyAppSecurityConfig(DbUsersInitial initial, JwtAuthenticationFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
    // actually that code must be presented
    // in the user registration service
    // we put it here only because we use H2 in-memory database
    log.info(":::: >> Populating initial users into Database...... >> ::::");
    initial.populate();
    log.info(":::: >> Populating initial users into Database. ...done >> ::::");
  }

  /**
   * create an array of all resources,
   * which must be available without authorization
   *
   * we just combine following part together:
   * 1. resources from static resource configurer (mapped directly to folders)
   * 2. API authorization endpoints: API_AUTH
   * 3. API free resources:
   * and
   */
  private String[] resources_permit_all() {
    return Stream.of(StaticResourcesConfig.MAP, API_AUTH, API_FREE)
        .flatMap(Arrays::stream).toArray(String[]::new);
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
        .antMatchers(resources_permit_all()).permitAll()
        .antMatchers("/api/home/**").authenticated()
        // coarse-grain approach
        .antMatchers("/api/admin/**").hasRole("ADMIN")
        // alternate fine-grain approach
//        .antMatchers(HttpMethod.GET, "/api/admin/**").permitAll()
//        .antMatchers(HttpMethod.PUT, "/api/admin/**").hasRole("USER")
//        .antMatchers(HttpMethod.PATCH, "/api/admin/**").hasRole("ADMIN")
        .antMatchers("/api/me/**").hasRole("USER")
        .antMatchers("/api/news/**").hasAnyRole("ADMIN", "USER")
        // there is no way to configure requests after this line
        .anyRequest().authenticated();

    // add our filter
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
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

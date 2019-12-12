package app.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

  public MySecurityConfig(DbUsersInitial initial) {
    log.info(":::: >> Populating initial users into Database...... >> ::::");
    // actually that code must be presented
    // in the user registration service
    // we put it here only because we use H2 in-memory database
    initial.populate();
    log.info(":::: >> Populating initial users into Database. done >> ::::");
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
        // .hasAuthority(‘ROLE_ADMIN') is similar to hasRole(‘ADMIN')
        // because the ‘ROLE_‘ prefix gets added automatically.
        // and we can use authorities in user building
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/guest/**").permitAll()
        .antMatchers("/home/**").authenticated()

        // special option to avoid applying `rememberMe` option
        .antMatchers("/admin/**").fullyAuthenticated()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/me/**").hasRole("USER")
        .antMatchers("/news/**").hasAnyRole("ADMIN", "USER")

        // almost any syntax available here. see the documentation
//        .antMatchers("/news/**").access("hasRole('ADMIN') and hasRole('DBA')")

//        .antMatchers(HttpMethod.GET, "/api").permitAll()
//        .antMatchers(HttpMethod.POST, "/api").hasAnyAuthority("USER", "ADMIN")
        // there is no way to configure requests after this line
        .anyRequest().authenticated();

    // login handling
    http
        .formLogin()
//        .loginPage("/login7") // you can write your own
//          .usernameParameter("...")
//          .passwordParameter("...")
//          .successForwardUrl("...")
//          .failureForwardUrl("...")
//          .failureHandler(null)
        .permitAll();

    // logout handling
//    http
//        .logout()
//        .invalidateHttpSession(true)
//        .clearAuthentication(true)
//        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////        .logoutSuccessUrl("/logout-success")
//        .permitAll();
  }
}

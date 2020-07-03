package app.conf.sec;

import app.service.XUserCreationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Log4j2
@Configuration
@EnableWebSecurity
public class SecAppSecurityConfig extends WebSecurityConfigurerAdapter {

  public SecAppSecurityConfig(XUserCreationService x) {
    x.create();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/", "/guest").permitAll()
        .antMatchers("/home").authenticated() // for any user
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/me").hasRole("USER")
        .antMatchers("/news").hasAnyRole("USER", "ADMIN")
        .anyRequest().authenticated();

    http
        .rememberMe();

    http
        .formLogin()
//        .loginPage("/login")
//        .usernameParameter("username")
//        .passwordParameter("password")
//        .successForwardUrl("/profile")
//        .failureForwardUrl("/wrong_password")
//        .failureHandler(new AuthenticationFailureHandler() {
//          @Override
//          public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//
//          }
//        })
        .permitAll();
  }

}

package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/       - default behavior (auth only)
 * http://localhost:8080/guest  - any unregistered
 * http://localhost:8080/home   - any authenticated
 * http://localhost:8080/admin  - authorized with ADMIN role
 * http://localhost:8080/me     - authorized with USER role
 * http://localhost:8080/news   - authorized with any USER or ADMIN role
 *
 * http://localhost:8080/login  - login link added by spring security
 * http://localhost:8080/logout - logout link added by spring security
 *
 * http://localhost:8080/h2-console - H2 web console
 *
 * spring.datasource.url = jdbc:h2:mem:testdb
 * spring.datasource.username = sa
 * spring.datasource.password = sa
 *
 */
@SpringBootApplication
public class SpringSecurityApp09 {
  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityApp09.class, args);
  }
}

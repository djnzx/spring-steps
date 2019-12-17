package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/h2-console - H2 web console
 *
 * http://localhost:8080/api/login    - POST: authentication
 * http://localhost:8080/api/logout   - POST: invalidation
 * http://localhost:8080/api/register - POST: registration
 *
 * http://localhost:8080/api/guest  - GET - any unregistered
 * http://localhost:8080/api/home   - GET - any authenticated
 * http://localhost:8080/api/admin  - GET - authorized with ADMIN role
 * http://localhost:8080/api/me     - GET - authorized with USER role
 * http://localhost:8080/api/news   - GET - authorized with any USER or ADMIN role
 *
 * spring.datasource.url = jdbc:h2:mem:testdb
 * spring.datasource.username = sa
 * spring.datasource.password = sa
 *
 * user   password   roles
 * ----- ---------- ------------
 * jim      123      USER
 * john     234      ADMIN
 * jack     345      ADMIN, USER
 * joe      456      - no roles
 * -----------------------------
 *
 * static mappings added:
 * http://localhost:8080/img/forever.png
 * http://localhost:8080/js/bootstrap.js
 * http://localhost:8080/css/bootstrap.css
 * http://localhost:8080/html/1.html
 *
 */
@SpringBootApplication
public class SpringSecurity43App {
  public static void main(String[] args) {
    SpringApplication.run(SpringSecurity43App.class, args);
  }
}

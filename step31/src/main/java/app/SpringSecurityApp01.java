package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/       - any unregistered
 * http://localhost:8080/guest  - any unregistered
 * http://localhost:8080/home   - any authenticated
 * http://localhost:8080/admin  - authorized with ADMIN role
 * http://localhost:8080/me     - authorized with USER role
 * http://localhost:8080/news   - authorized with any USER or ADMIN role
 */
@SpringBootApplication
public class SpringSecurityApp01 {
  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityApp01.class, args);
  }
}

package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/
 * http://localhost:8080/guest
 * http://localhost:8080/home
 * http://localhost:8080/admin
 * http://localhost:8080/me
 * http://localhost:8080/news
 */
@SpringBootApplication
public class SpringStep02Mapping {
  public static void main(String[] args) {
    SpringApplication.run(SpringStep02Mapping.class, args);
  }
}

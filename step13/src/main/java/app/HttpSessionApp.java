package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * javax.servlet.http.HttpSession
 * http://localhost:8080/ses             - create / access object in a session
 * http://localhost:8080/ses/rm_one      - remove one object
 * http://localhost:8080/ses/invalidate  - invalidate the whole session (remove all objects)
 * http://localhost:8080/ses  - javax.servlet.http.HttpSession
 * http://localhost:8080/sess - HttpSession by Spring
 */
@SpringBootApplication
public class HttpSessionApp {
  public static void main(String[] args) {
    SpringApplication.run(HttpSessionApp.class);
  }
}

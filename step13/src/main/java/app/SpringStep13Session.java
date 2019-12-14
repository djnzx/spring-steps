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
public class SpringStep13Session {
  public static void main(String[] args) {
    SpringApplication.run(SpringStep13Session.class);
  }
}

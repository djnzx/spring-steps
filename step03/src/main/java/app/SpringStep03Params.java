package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/msg               - Hello World
 * http://localhost:8080/msg/one?a=3&b=4&msg=Hello+from+alex
 * http://localhost:8080/msg/two/5/7/Hello%20From%20Alex
 * and a lot of POST requests,
 * see detailed comments in the `MessageController.java`
 */
@SpringBootApplication
public class SpringStep03Params {
  public static void main(String[] args) {
    SpringApplication.run(SpringStep03Params.class);
  }
}

package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/person     - GET    - get all
 * http://localhost:8080/person/1   - GET    - get 1
 * http://localhost:8080/person/1   - DELETE - delete 1
 * http://localhost:8080/person     - PUT    - save 1
 * http://localhost:8080/person/cr  - POST   - create two initial users
 *
 * http://localhost:8080/h2-console
 */
@SpringBootApplication
public class SpringDataStep22Relations1N {
  public static void main(String[] args) {
    SpringApplication.run(SpringDataStep22Relations1N.class);
  }
}

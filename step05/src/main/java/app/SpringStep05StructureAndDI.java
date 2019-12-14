package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/fmt/f_1     // by name, p1
 * http://localhost:8080/fmt/f_2     // by name, p2
 * http://localhost:8080/fmt/f_prim  // by @Primary, p2
 * http://localhost:8080/fmt/f_qual  // by @Qualifier,
 */
@SpringBootApplication
public class SpringStep05StructureAndDI {
  public static void main(String[] args) {
    SpringApplication.run(SpringStep05StructureAndDI.class);
  }
}

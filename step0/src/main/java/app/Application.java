package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * http://localhost:8080/users
 */
@RestController
@SpringBootApplication
public class Application {

  static class Person {
    private String name;
    Person(String name) {
      this.name = name;
    }
    public String getName() {
      return name;
    }
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @GetMapping("/users")
  public List<Person> btdf () {
    return Arrays.asList(
        new Person("Jim"),
        new Person("John"));
  }

}

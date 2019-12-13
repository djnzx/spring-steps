package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * http://localhost:8080/person
 */
@Controller
@SpringBootApplication
public class ApplicationIntro {

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
    SpringApplication.run(ApplicationIntro.class);
  }

  @ResponseBody
  @RequestMapping("/person")
  public List<Person> handle () {
    return Arrays.asList(
        new Person("Jim"),
        new Person("John"));
  }

}

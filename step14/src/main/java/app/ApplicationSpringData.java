package app;

import app.service.PersonService;
import app.service.ResponsibilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * http://localhost:9000/r
 * http://localhost:9000/r/cr
 * http://localhost:9000/p
 *
 * http://localhost:9000/h2-console
 */
@Slf4j
@SpringBootApplication
public class ApplicationSpringData {
  public static void main(String[] args) {
    SpringApplication.run(ApplicationSpringData.class);
  }

  @Bean
  public CommandLineRunner create_initial_data(
      ResponsibilityService responsibilityService,
      PersonService personService) {
    return args -> {
      log.info("Creating initial data: (responsibilities and persons)");
      responsibilityService.create_initial();
      personService.create_initial();
    };
  }
}

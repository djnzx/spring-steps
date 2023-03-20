package app.config;

import app.client.PersonClient;
import app.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class Beans {

  private final PersonClient personClient;

  @Bean
  public RestTemplate createRestTemplate() {
    return new RestTemplate();
  }


  @Bean
  public CommandLineRunner run() {
      return args -> {
          Person byId = personClient.getById(33);
          System.out.println(byId);
      };
  }

}

package app.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AfterStart {

  @Bean
  public CommandLineRunner demo() {
    return (args) -> {
      log.info("::::: CommandLineRunner ::::: called");
   };
  }
}

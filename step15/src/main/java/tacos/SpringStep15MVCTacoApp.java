package tacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tacos.entity.Ingredient;
import tacos.entity.Ingredient.Type;
import tacos.repo.IngredientRepository;

import java.util.Arrays;

/**
 * http://localhost:8080
 */
@Slf4j
@SpringBootApplication
public class SpringStep15MVCTacoApp {

  public static void main(String[] args) {
    SpringApplication.run(SpringStep15MVCTacoApp.class, args);
  }

  /**
   * autorun :)
   * it runs after Tomcat started
   */
  @Bean
  public CommandLineRunner dataLoader(IngredientRepository repo) {
    return args -> {
      log.info("============= CommandLineRunner");
      repo.saveAll(Arrays.asList(
          new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
          new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
          new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
          new Ingredient("CARN", "Carnitas", Type.PROTEIN),
          new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
          new Ingredient("LETC", "Lettuce", Type.VEGGIES),
          new Ingredient("CHED", "Cheddar", Type.CHEESE),
          new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
          new Ingredient("SLSA", "Salsa", Type.SAUCE),
          new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
      ));
    };
  }

}

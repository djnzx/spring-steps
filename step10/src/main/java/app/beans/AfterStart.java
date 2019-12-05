package app.beans;

import app.entity.Customer;
import app.repo.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AfterStart {

  private static final Logger log = LoggerFactory.getLogger(AfterStart.class);

  @Bean
  public CommandLineRunner demo(CustomerRepository repository) {
    return (args) -> {
      // save a couple of customers
      repository.save(new Customer("Jack", "Bauer"));
      repository.save(new Customer("Chloe", "O'Brian"));
      repository.save(new Customer("Kim", "Bauer"));
      repository.save(new Customer("David", "Palmer"));
      repository.save(new Customer("Michelle", "Dessler"));
      repository.save(new Customer("Alex", "BR"));

      // fetch all customers
      log.info("Customers found with findAll():");
      log.info("-------------------------------");
      for (Customer customer : repository.findAll()) {
        log.info(customer.toString());
      }
      log.info("");

      // fetch an individual customer by ID
      repository.findById(1L)
          .ifPresent(customer -> {
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");
          });

      // fetch customers by last name
      log.info("Customer found with findByLastName('Bauer'):");
      log.info("--------------------------------------------");
      repository.findByLastName("Bauer").forEach(bauer -> {
        log.info(bauer.toString());
      });
      log.info("Customer found with findByLastNameIgnoreCaseContaining('b'):");
      log.info("---------------------------------------------------------");
      repository.findByLastNameIgnoreCaseContaining("b").forEach(b -> {
        log.info(b.toString());
      });
      // for (Customer bauer : repository.findByLastName("Bauer")) {
      // 	log.info(bauer.toString());
      // }
      log.info("");
    };
  }
}

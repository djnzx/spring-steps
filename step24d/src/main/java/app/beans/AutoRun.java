package app.beans;

import app.entity.Person;
import app.entity.Phone;
import app.repo.PersonRepo;
import app.repo.PhoneRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@Configuration
public class AutoRun {

  private final PersonRepo personRepo;
  private final PhoneRepo phoneRepo;

  public AutoRun(PersonRepo personRepo, PhoneRepo phoneRepo) {
    this.personRepo = personRepo;
    this.phoneRepo = phoneRepo;
  }

  @Bean
  @Order(1)
  public CommandLineRunner populate_phones() {
    return args -> {
      /**
       * adding entities without relations is as easy as 1,2,3
       *
       * 1. create an entity or collection of entities
       * 2. call repo.save(entity) or repo.saveAll(collection)
       *
       * creating phones WITHOUT RELATION to person
       */
      phoneRepo.saveAll(Arrays.asList(
          new Phone("111-111-111"), // 1
          new Phone("111-111-112"), // 2
          new Phone("111-111-113")  // 3
      ));
      phoneRepo.saveAll(Arrays.asList(
          new Phone("111-222-114"), // 4
          new Phone("111-222-112")  // 5
      ));
      phoneRepo.saveAll(Arrays.asList(
          new Phone("111-333-114") // 6
      ));
      log.info(":1: Phones have been created");
    };
  }

  @Bean
  @Order(2)
  public CommandLineRunner populate_people() {
    return args -> {
      /**
       * adding entities without relations is as easy as 1,2,3
       *
       * 1. create an entity or collection of entities
       * 2. call repo.save(entity) or repo.saveAll(collection)
       *
       * creating persons WITHOUT RELATION to phone
       */
      personRepo.saveAll(Arrays.asList(
          new Person("Jim"),  // 7
          new Person("Jack"), // 8
          new Person("Joe")   // 9
      ));
      log.info(":2: Persons have been created");
    };
  }

  @Bean
  @Order(3)
  public CommandLineRunner populate_people_with_phones() {
    return args -> {
      /**
       * creating/adding entities WITH FRESHLY created relations (1:N) is as easy as 1,2
       * 1. create related entities collections: mick_phones, mario_phones
       * 2. create an entity or collection of entities: mick, mario
       * 3. don't forget to pass the related data to their constructors
       * 4. call repo.save(entity) or repo.saveAll(collection)
       *
       * creating persons WITH RELATION to phones (all new)
       */
      Set<Phone> mick_phones =
          Stream.of("100-00-01", "100-00-02").map(Phone::new).collect(Collectors.toSet()); // 11,12
      Set<Phone> mario_phones =
          Stream.of("200-00-01", "200-00-02").map(Phone::new).collect(Collectors.toSet()); // 14,15

      Person mick  = new Person(0, "Mick", mick_phones);  // 10
      Person mario = new Person(0, "Mario", mario_phones); // 13

      personRepo.saveAll(Arrays.asList(mick, mario));
      log.info(":3: Persons WITH phones have been created");
    };
  }

  @Bean
  @Order(4)
  public CommandLineRunner populate_people_with_already_existed_phones() {
    return new CommandLineRunner() {
      @Transactional
      @Override
      public void run(String... args) throws Exception {
        /**
         * creating/adding entities WITH ALREADY EXISTED relations (1:N) is as easy as 1....
         * 1. fetch all ALREADY EXISTED DATA which you want to add as a NEW RELATION to the master entity:
         *    - repo.findXXX()
         * 2. create all FRESH DATA which you want to add as a NEW RELATION to the master entity:
         *    - alexey_phones
         *    - alexandr_phones
         * 3. add ALREADY EXISTED DATA to FRESH DATA:
         *    - mick_ph.alexey_phones(existed_phones)
         *    - alexandr_phones.addAll(existed_phones)
         * 4. create an entity or collection of entities:
         *    - alexey
         *    - alexandr
         * 5. don't forget to pass the related data to their constructors
         *    - new Person(0, "Alexey", alexey_phones)
         *    - new Person(0, "Alexandr", alexandr_phones)
         * 6. save them to repository:
         *    - call repo.save(entity) or repo.saveAll(collection)
         * 7. wrap everything into one transaction by @Transactional annotation on method...
         *    unless you want to get... InvalidDataAccessApiUsageException: detached entity passed to persist :)
         *
         * creating persons WITH RELATION to phones
         * partially new, partially already existing ones
         */
        Collection<Phone> existed_phones = phoneRepo.findAllByNumberStartsWith("111-111"); // ids:: 1,2,3

        Set<Phone> alexey_phones =
            Stream.of("300-00-01", "300-00-02").map(Phone::new).collect(Collectors.toSet()); // 17,18
        Set<Phone> alexandr_phones =
            Stream.of("400-00-01", "400-00-02").map(Phone::new).collect(Collectors.toSet()); // 20,21

        alexey_phones.addAll(existed_phones);
        alexandr_phones.addAll(existed_phones);

        Person alexey = new Person(0, "Alexey", alexey_phones);  // 16
        Person alexandr = new Person(0, "Alexandr", alexandr_phones); // 19

        // persisting entities
        personRepo.saveAll(Arrays.asList(alexey, alexandr));
        log.info(":4: Persons WITH phones have been created");
      }
    };
  }

  @Bean
  @Order(5)
  public CommandLineRunner adding_one_phone_to_already_existing_person() {
    return new CommandLineRunner() {
      @Transactional
      @Override
      public void run(String... args) throws Exception {
        /**
         * just ADDING NEW DATA as A NEW RELATIONS to EXISTED ONES is as easy as 1,2,3
         * 1. fetch entity you wanna modify:
         *    - personRepo.findByNameIgnoreCase("Alexey");
         * 2. add new RELATIONS
         *    - p.addNewPhone(new Phone("777-77-77"));
         * 3. wrap everything into one transaction by @Transactional
         * 4. you DON'T need to call any repo.save....
         *
         * updating persons WITH RELATION to phones
         * partially new, partially already existing ones
         */
        Optional<Person> found = personRepo.findByNameIgnoreCase("Alexey");
        found.ifPresent(p -> {
          p.addNewPhone(new Phone("777-77-77")); // 22
          p.addNewPhone(new Phone("777-77-78")); // 23
        });

        log.info(":5: NEW Phones 777-77-77 and 777-77-78 have been added to person Alexey");
      }
    };
  }

  @Bean
  @Order(6)
  public CommandLineRunner delete_one_phone_from_already_existing_person() {
    return new CommandLineRunner() {
      @Transactional
      @Override
      public void run(String... args) throws Exception {
        /**
         * DELETING RELATIONS from EXISTED ONES is as easy as 1,2,3
         * 1. fetch entity you wanna modify:
         *    - personRepo.findByNameIgnoreCase("Alexey");
         * 2. delete the RELATIONS
         *    - p.deletePhoneByPredicate(ph -> ph.getNumber().equals("777-77-78"));
         * 3. wrap everything into one transaction by @Transactional
         * 4. you DON'T need to call any repo.save....
         *
         * NOTICE: this approach deletes ONLY RELATION,
         * but NOT THE ENTITY, because there is no any proof/guarantee
         * that this entity is not related to other entities.
         * because of MANY-TO-MANY relation...
         *
         * updating persons WITH RELATION to phones
         * partially new, partially already existing ones
         */
        Optional<Person> found = personRepo.findByNameIgnoreCase("Alexey");
        found.ifPresent(p -> {
          p.deletePhoneByPredicate(ph -> ph.getNumber().equals("777-77-78"));
        });

        log.info(":6: Phone 777-77-78 has been removed from person Alexey");
      }
    };
  }
// http://localhost:8080/h2-console
}

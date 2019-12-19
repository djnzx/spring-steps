package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/h2-console
 *
 * http://localhost:8080/ph/24    // {"id":24,"number":"P1"}
 * select phone0_.ph_id as ph_id1_1_0_, phone0_.ph_number as ph_numbe2_1_0_ from phone phone0_ where phone0_.ph_id=24
 *
 * http://localhost:8080/p/26     // {"id":26,"name":"Dinosaur"}
 * select person0_.p_id as p_id1_0_0_, person0_.p_name as p_name2_0_0_ from person person0_ where person0_.p_id=26
 *
 * http://localhost:8080/phn/24   // {"id":24,"number":"P1"}
 * select persons0_.phone_id as phone_id2_2_0_, persons0_.person_id as person_i1_2_0_, person1_.p_id as p_id1_0_1_, person1_.p_name as p_name2_0_1_ from r_person_phone persons0_ inner join person person1_ on persons0_.person_id=person1_.p_id where persons0_.phone_id=24
 *
 * http://localhost:8080/pn/26    // {"id":26,"name":"Dinosaur","phones":[{"id":25,"number":"P2"},{"id":24,"number":"P1"}]}
 * select phones0_.person_id as person_i1_2_0_, phones0_.phone_id as phone_id2_2_0_, phone1_.ph_id as ph_id1_1_1_, phone1_.ph_number as ph_numbe2_1_1_ from r_person_phone phones0_ inner join phone phone1_ on phones0_.phone_id=phone1_.ph_id where phones0_.person_id=26
 *
 */
@SpringBootApplication
public class SpringDataStep24select {
  public static void main(String[] args) {
    SpringApplication.run(SpringDataStep24select.class);
  }
}

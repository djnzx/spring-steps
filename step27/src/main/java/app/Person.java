package app;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@Entity
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer age;

  private String name;

  public Person(Integer age, String name) {
    this.age = age;
    this.name = name;
  }

  static Person random() {
    Random r = new Random();
    String name = Stream.generate(() -> r.nextInt('Z' - 'A') + 'A').limit(20)
        .map(x -> String.valueOf(((char) (int) x)))
        .collect(Collectors.joining());

    return new Person(r.nextInt(30) + 20, name);
  }
}

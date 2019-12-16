package app.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
@Entity
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private final long id;

  @Column(name = "name")
  private final String name;

  /**
   * Shared Primary Key approach
   *
   * we will not have extra field on `main entity` to reference om `related entity`
   * we will use our `PK` as `related entity PK`
   *
   * we dont provide the @JoinColumn
   * because Person.PK will be server as `PK` for the `related entity`
   *
   * `person`   - field to access to related entity from Java
   */
  @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
  private Extra extra;

  public Person(String name) {
    this.id = 0;
    this.name = name;
  }
}

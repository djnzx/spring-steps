package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "person")
public class Person {
  @Id
  // to enable Postgres sequence
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "name")
  private String name;

  /**
   * @OneToMany
   * that's not a column
   * that's a field to keep a relation on Java LEVEL
   *
   * @OneToMany(mappedBy="person")"
   * `person` is a field name on the `related entity` (Phone)
   *
   */
  @OneToMany(mappedBy="person")
  private Set<Phone> phones;

}

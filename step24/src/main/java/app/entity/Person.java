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
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "p_id")
  private long id;

  @Column(name = "p_name")
  private String name;

  /**
   * @ManyToMany
   * that's not a column
   * that's a field to keep a relation on Java LEVEL
   *
   * @ManyToMany(mappedBy="persons")"
   * `persons` is a field name on the `related entity` (Phone)
   *
   */
  @ManyToMany(mappedBy="persons")
  private Set<Phone> phones;

}

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
@Table(name = "phone")
public class Phone {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ph_id")
  private long id;

  @Column(name = "ph_name")
  private String name;

  /**
   * that's not a column
   * that's a field to keep a relation on Java LEVEL
   *
   * according to `@JoinTablename = "r_person_phone",...`
   * 3rd table will be created
   * to keep that relation on the Database LEVEL
   */
  @ManyToMany
  @JoinTable(name = "r_person_phone",
      joinColumns =
          { @JoinColumn(name = "phone_id", referencedColumnName = "ph_id") },
      inverseJoinColumns =
          { @JoinColumn(name = "person_id", referencedColumnName = "p_id") }
  )
  private Set<Person> persons;

}

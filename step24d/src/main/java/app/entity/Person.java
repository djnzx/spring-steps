package app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;

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
  @JsonManagedReference
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "r_person_phone"
      ,joinColumns =        { @JoinColumn(name = "person_id", referencedColumnName = "p_id") }
      ,inverseJoinColumns = { @JoinColumn(name = "phone_id", referencedColumnName = "ph_id") }
  )
  private Collection<Phone> phones = new HashSet<>();

  public void addNewPhone(Phone phone) {
    phones.add(phone);
  }

  public void deletePhoneByPredicate(Predicate<Phone> p) {
    phones.removeIf(p);
  }

  public Person(String name) {
    this.name = name;
  }
}

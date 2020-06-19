package app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "person")
public class Person {
  @Id
  // to enable Postgres sequence
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  public Person(String name, Iterable<Phone> phones) {
    this.name = name;
    this.phones = StreamSupport.stream(phones.spliterator(), false).collect(Collectors.toSet());
  }
}

package app.entity;

import app.controller.Views;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

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
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "p_id")
  @JsonView(Views.NoNested.class)
  private long id;

  @Column(name = "p_name")
  @JsonView(Views.NoNested.class)
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
  @ManyToMany(
      cascade = { CascadeType.PERSIST, CascadeType.MERGE },
//      fetch = FetchType.EAGER
      fetch = FetchType.LAZY
  )
  @JoinTable(name = "r_person_phone"
      ,joinColumns =        { @JoinColumn(name = "person_id", referencedColumnName = "p_id") }
      ,inverseJoinColumns = { @JoinColumn(name = "phone_id", referencedColumnName = "ph_id") }
  )
  @JsonView(Views.AllowPersonToPhone.class)
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

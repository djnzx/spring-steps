package app.entity;

import app.controller.Views;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "phone")
public class Phone {
  @Id
  // to enable Postgres sequence
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ph_id")
  @JsonView(Views.NoNested.class)
  private long id;

  @Column(name = "ph_number")
  @JsonView(Views.NoNested.class)
  private String number;

  /**
   * that's not a column
   * that's a field to keep a relation on Java LEVEL
   *
   * according to `@JoinTablename = "r_person_phone",...`
   * 3rd table will be created
   * to keep that relation on the Database LEVEL
   */
  @ManyToMany(
      cascade = { CascadeType.PERSIST, CascadeType.MERGE },
//      fetch = FetchType.EAGER,
      fetch = FetchType.LAZY,
      mappedBy="phones")
  @JsonView(Views.AllowPhoneToPerson.class)
  private Collection<Person> persons = new HashSet<>();

  public Phone(String number) {
    this.number = number;
  }
}

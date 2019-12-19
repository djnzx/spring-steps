package app.entity;

import app.controller.Views;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ph_id")
  @JsonView(Views.Basic.class)
  private long id;

  @Column(name = "ph_number")
  @JsonView(Views.Basic.class)
  private String number;

  /**
   * that's not a column
   * that's a field to keep a relation on Java LEVEL
   *
   * according to `@JoinTablename = "r_person_phone",...`
   * 3rd table will be created
   * to keep that relation on the Database LEVEL
   */
  @JsonBackReference
  @ManyToMany(
      cascade = { CascadeType.PERSIST, CascadeType.MERGE },
//      fetch = FetchType.EAGER,
      fetch = FetchType.LAZY,
      mappedBy="phones")
  @JsonView(Views.WithNested.class)
  private Collection<Person> persons = new HashSet<>();

  public Phone(String number) {
    this.number = number;
  }
}

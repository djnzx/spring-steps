package app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
  // to enable Postgres sequence
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ph_id")
  private long id;

  @Column(name = "ph_number")
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
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="phones")
  private Collection<Person> persons = new HashSet<>();

  public Phone(String number) {
    this.number = number;
  }
}

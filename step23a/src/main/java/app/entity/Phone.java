package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "phone")
public class Phone {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;

  @Column(name = "name")
  private String name;

  /**
   * that's not a column
   * that's a field to keep a relation on Java LEVEL
   *
   * all the details
   * how to keep that relation on the Database LEVEL
   * will be taken from `mirror` entity
   */
  @ManyToOne
  private Person person;

}

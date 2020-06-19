package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

  @OneToOne(cascade = CascadeType.ALL)
  @JoinTable(name = "r_person_extra",
      joinColumns =
          { @JoinColumn(name = "person_id",
              referencedColumnName = "p_id") },
      inverseJoinColumns =
          { @JoinColumn(name = "extra_id",
              referencedColumnName = "x_id") })
  private Extra3 extra;

}

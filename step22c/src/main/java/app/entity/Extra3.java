package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "xtra3")
public class Extra3 {
  @Id
  // to enable Postgres sequence
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "x_id")
  private long id;

  @Column(name = "x_name")
  private String name;

  // field name from another table
  @OneToOne(mappedBy = "extra")
  private Person person;

}

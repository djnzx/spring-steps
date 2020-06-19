package app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "extra")
public class Extra {

  @Id
  // to enable Postgres sequence
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "x_id")
  private long id;

  @Column(name = "x_info")
  private String info;

  /**
   * `extra`    - field name to reference on `main entity`
   */
  @OneToOne(mappedBy = "extra") // attribute which holds the relation
  private Person person;
}

package app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "extra")
public class Extra {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

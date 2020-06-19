package app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "extra")
public class Extra {

  /**
   * this column actually will be called `person_id`
   */
  @Id
  // to enable Postgres sequence
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "x_id")
  private long id;

  @Column(name = "x_info")
  private String info;

  /**
   * `person`    - field name to reference on `main entity`
   *
   * @MapsId tells Hibernate to use the id column of address as both primary key and foreign key.
   * Notice that the @Id column of the Address entity no longer uses the @GeneratedValue annotation.
   *
   */
  @OneToOne
  @MapsId
  private Person person;
}

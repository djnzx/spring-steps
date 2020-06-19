package app.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
@Entity
@Table(name = "person")
public class Person {

  @Id
  // to enable Postgres sequence
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private final long id;

  @Column(name = "name")
  private final String name;

  /**
   * Foreign Key approach
   *
   * we will have extra field to reference om target table
   * that column `extra_id` will be created for join purposes by Hibernate
   *
   * `extra_id` - my column name for holding joining purposes
   * `x_id`     - column name for PK from related entity
   *
   * `extra`    - field to access to related entity from Java
   */
  @OneToOne//(cascade = CascadeType.ALL)
  @JoinColumn(name = "extra_id", referencedColumnName = "x_id")
  private Extra extra;

  public Person(String name) {
    this.id = 0;
    this.name = name;
  }
}

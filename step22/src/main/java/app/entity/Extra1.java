package app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "extra1")
public class Extra1 {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;

  @Column(name = "info")
  private String info;

  @OneToOne(mappedBy = "extra1") // attribute which holds the relation
  private Person person;
}

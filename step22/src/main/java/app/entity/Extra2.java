package app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "extra2")
public class Extra2 {

  @Id
  @Column(name = "id") // actual field name will be `person_id`: fieldname: person + id
  private long id;

  @Column(name = "innfo")
  private String info;

  // @MapsId tells Hibernate to use the id column of address as both primary key and foreign key.
  // Notice that the @Id column of the Address entity no longer uses the @GeneratedValue annotation.
  @OneToOne
  @MapsId
  private Person person;
}

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
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private final long id;

  @Column(name = "name")
  private final String name;


  // ========== Foreign Key approach (we well have extra field to reference om target table.PK)
  // that exactly means, that column `extra_id` will be created for join purposes
  // and Extra1 will be joined by its `id` field
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "extra1_id", referencedColumnName = "id")
  private Extra1 extra1;

  // ========== Shared Primary Key (we will use our PK as target table.PK)
  // we dont provide the @JoinColumn because
  // Person.PK will be stored in the target table
  @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
  private Extra2 extra2;



  public Person(String name) {
    this.id = 0;
    this.name = name;
  }
}

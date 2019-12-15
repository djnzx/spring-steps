package app.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
@Entity
@Table(name = "persson")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private final long id;

  @NotNull
  @Size(min=3, message="Name must be at least 3 characters long")
  private final String name;

  @Column(name = "exxtra")
  private final String extra;

  private Date createdAt;

  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }

  public Person(String name) {
    this.id = 0;
    this.name = name;
    this.extra = null;
  }

  /**
   * these annotations @ManyToMany() or @OneToMany
   * 1.   IMMEDIATELY creates RELATIONS table
   *      PERSON_RESPONSIBILITIES (PERSON_ID, RESPONSIBILITY_ID)
   *
   * 2.   GIVES ABILITY TO USE JOIN "OUT OF THE BOX"
   *      all queries automatically appended with "inner join on child table"
   * 2.1. In you want to stop these nested serialization, just put @JsonIgnore
   * 2.2. If you want to stop these nested subquery - put fetch = FetchType.LAZY
   *      in that case SQL will be performed only after corresponding access to field.
   */
}

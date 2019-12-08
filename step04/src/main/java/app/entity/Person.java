package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
@Entity
@Table(name = "person")
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
//  @JsonIgnore
  @ManyToMany(targetEntity = Responsibility.class)
  private List<Responsibility> responsibilities = new ArrayList<>();

////  @Size(min=1, message="You must have at least 1 responsibility")

}

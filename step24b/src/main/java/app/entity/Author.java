package app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Author {

  @Id
  // to enable Postgres sequence
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "a_id")
  private int id;

  @Column(name = "a_name")
  private String name;

  /**
   * @ManyToMany
   * that's not a column
   * that's a field to keep a relation on Java (Hibernate) level
   *
   * because of absence `mappedBy` property
   * we create dedicated table `author_books`
   * to maintain unidirectional relation
   */
  @ManyToMany
  private Set<Book> books;
}

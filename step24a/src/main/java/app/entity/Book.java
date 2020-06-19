package app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Data
@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // to enable Postgres sequence
  @Column(name = "b_id")
  private int id;

  @Column(name = "b_name")
  private String name;

  /**
   * @ManyToMany
   * that's not a column
   * that's a field to keep a relation on Java (Hibernate) level
   *
   * `mappedBy` property - should be set only on one side (not on both entities)
   *
   * because of presence `mappedBy` property - only one table `author_books`
   * is created to handle BI-directional relations.
   * note, that we have a composite primary key(b_id, a_id)
   */
  @ManyToMany(mappedBy = "books")
  private List<Author> authors;

  /**
   * smart way to handle
   * 1:N or 1:1 via M:N
   */
  public Optional<Author> author() {
    return authors == null || authors.size() == 0 ?
        Optional.empty() : Optional.of(authors.get(0));
  }

}

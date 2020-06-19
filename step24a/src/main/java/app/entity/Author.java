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
   * `mappedBy` property - should be set only on one side (not on both entities)
   *
   * because of presence `mappedBy` property - only one table `author_books`
   * is created to handle BI-directional relations.
   * note, that we have a composite primary key(b_id, a_id)
   */
  @ManyToMany
  /**
   * we can customize the table name,
   * its column names
   *
   * @JoinTable - must be set up only on the one side,
   * on the side opposite to @ManyToMany(mappedBy = ...)
   */
  @JoinTable(name = "authors_books",
      joinColumns = { @JoinColumn(name = "author_id", referencedColumnName = "a_id") },
      inverseJoinColumns = { @JoinColumn(name = "book_id", referencedColumnName = "b_id") }
  )
  private Set<Book> books;
}

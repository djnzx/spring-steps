package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "author")
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "a_id")
  private long id;

  @Column(name = "a_name")
  private String name;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinTable(name = "r_author_book",
      joinColumns =
          { @JoinColumn(name = "author_id", referencedColumnName = "a_id") },
      inverseJoinColumns =
          { @JoinColumn(name = "book_id", referencedColumnName = "b_id") })
  private Book book;

}

package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "b_id")
  private long id;

  @Column(name = "b_name")
  private String name;

  // field name from another table
  @OneToOne(mappedBy = "book")
  private Author author;

}

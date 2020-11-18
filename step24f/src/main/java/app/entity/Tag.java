package app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column
  String name;

  public Tag(String name) {
    this.name = name;
  }

  public Tag(String name, List<Post> posts) {
    this.name = name;
    this.posts = posts;
  }

  @ManyToMany(mappedBy = "tags")
  private List<Post> posts = new ArrayList<>();

}

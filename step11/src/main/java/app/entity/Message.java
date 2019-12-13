package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private int user1;
  private int user2;
  private String content;
}

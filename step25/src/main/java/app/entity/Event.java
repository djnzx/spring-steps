package app.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {

  @Id
  @GeneratedValue
  private long pk;

  private long delta;

  @Formula(value = "(PK + DELTA)")
  private long total;

  private String name;

  public Event(String name, long delta) {
    this.name = name;
    this.delta = delta;
  }
}

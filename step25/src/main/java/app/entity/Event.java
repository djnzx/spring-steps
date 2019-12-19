package app.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Event {

  @Id
  @GeneratedValue
  private long pk;

  private long delta;

  @Formula(value = "(PK + DELTA)")
  private long total;

  private String name;

  protected Event() { }

  public Event(String name, long delta) {
    this.name = name;
    this.delta = delta;
  }

  public long getPk() {
    return pk;
  }

  public void setPk(long pk) {
    this.pk = pk;
  }

  public long getDelta() {
    return delta;
  }

  public void setDelta(long delta) {
    this.delta = delta;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("Event{pk=%d, name='%s'}", pk, name);
  }
}

package app.session;

import lombok.Data;

@Data
public class LongLivingCreature {
  private static int counter;
  private int id;
  //...
  private String name;

  public void newUser() {
    this.id = ++counter;
  }
}

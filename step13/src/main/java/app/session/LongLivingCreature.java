package app.session;

import lombok.Data;

@Data
public class LongLivingCreature {
  //...
  private String id;

  private int value;

  public void inc() {
    value++;
  }

  public LongLivingCreature(String id) {
    this.id = id;
    this.value = 0;
  }

}

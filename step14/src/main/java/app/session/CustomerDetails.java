package app.session;

import lombok.Data;

@Data
public class CustomerDetails {
  public final static String ATTR = "cd";

  // #1
  private String seat;

  // #2
  private String firstname;
  private String lastname;

  // #3
  private String cardno;
}

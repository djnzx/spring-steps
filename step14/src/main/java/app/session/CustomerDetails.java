package app.session;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDetails {
  public static final String ATTR = "cd";

  private String session;

  // form #1
  private String seat;

  // form #2
  private String firstname;
  private String lastname;

  // form #3
  private String cardno;

  public CustomerDetails(String session) {
    this.session = session;
  }
}

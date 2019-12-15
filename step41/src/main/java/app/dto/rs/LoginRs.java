package app.dto.rs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRs {
  private String message;
  private String token;
}

package app.api.dto.rs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterRs {
  private String message;

  public static RegisterRs Ok() {
    return new RegisterRs("Ok");
  }

  public static RegisterRs AlreadyExists() {
    return new RegisterRs("Error: User Already Exists");
  }
}

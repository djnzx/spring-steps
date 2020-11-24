package dtovalidation.dto;

import dtovalidation.valid.ValidPassword;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RqUserRegister {

  @Email
  private String email;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  @ValidPassword
  private String password;

}

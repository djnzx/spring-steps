package app;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Form {
  @NotNull
  String name;
  @NotNull
  @Size(min=3, max=30)
  String pass;
}

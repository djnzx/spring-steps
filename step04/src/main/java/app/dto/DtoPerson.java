package app.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class DtoPerson {

  @NotNull
  @Size(min=3, message="Name must be at least 3 characters long")
  private final String name;

  @Column(name = "exxtra")
  private final String extra;

  private List<String> responsibilities;

}

package app.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
@Entity
public class Responsibility {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private final long id;

  @NotNull
  @Size(min=3, message="Name must be at least 3 characters long")
  private final String name;

}

package app.api.dto;

import app.data.Ingredient;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * this class represents DTO with validation
 * between frontend and backend parts
 */
@Data
public class Taco {
  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  @Size(min=1, message="You must choose at least 1 ingredient")
  private List<Ingredient> ingredients;

  private Long id;

  private Date createdAt;
}

package app.jdbc;

import app.api.dto.Taco;

public interface TacoRepository  {
  Taco save(Taco design);
}

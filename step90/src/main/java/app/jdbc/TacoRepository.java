package app.jdbc;

import app.dto.Taco;

public interface TacoRepository  {
  Taco save(Taco design);
}

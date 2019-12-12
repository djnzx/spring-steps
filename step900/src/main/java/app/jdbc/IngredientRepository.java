package app.jdbc;

import app.data.Ingredient;

public interface IngredientRepository {
  Iterable<Ingredient> findAll();
  Ingredient findById(String id);
  Ingredient save(Ingredient ingredient);
}

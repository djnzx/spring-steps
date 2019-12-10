package tacos.repo;

import org.springframework.data.repository.CrudRepository;

import tacos.entity.Ingredient;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
  default List<Ingredient> findAllToList() {
    return StreamSupport.stream(findAll().spliterator(), false)
        .collect(Collectors.toList());
  }
}

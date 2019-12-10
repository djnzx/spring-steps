package tacos.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.entity.Ingredient;
import tacos.repo.IngredientRepository;

@Component
public class ConverterStringToIngredient implements Converter<String, Ingredient> {

  private IngredientRepository repo;

  @Autowired
  public ConverterStringToIngredient(IngredientRepository repo) {
    this.repo = repo;
  }

  @Override
  public Ingredient convert(String id) {
//    if (true) throw new IllegalArgumentException("experiments :)");
	  return repo.findById(id).orElse(null);
  }

}

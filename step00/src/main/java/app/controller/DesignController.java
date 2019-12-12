package app.controller;

import app.data.Ingredient;
import app.data.Ingredient.Type;
import app.dto.Order;
import app.dto.Taco;
import app.jdbc.IngredientRepository;
import app.jdbc.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order") // <1> specifies any model objects like the order attribute that should be kept in session and available across multiple requests, P70,71
public class DesignController {

  private final TacoRepository tacoRepository;
  private final IngredientRepository ingredientRepository;

  @Autowired
  public DesignController(TacoRepository tacoRepository, IngredientRepository ingredientRepository) {
    this.tacoRepository = tacoRepository;
    this.ingredientRepository = ingredientRepository;
  }

  @ModelAttribute(name = "order") // <2>. If there is no order in session - it will be created
  public Order order() {
    return new Order();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = Arrays.asList(
        new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
        new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        new Ingredient("CHED", "Cheddar", Type.CHEESE),
        new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        new Ingredient("SLSA", "Salsa", Type.SAUCE),
        new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
    );

    Type[] types = Ingredient.Type.values(); // WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE in lowercase

    for (Type type: types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }

    model.addAttribute("design", new Taco());

    return "design";
  }

  /**
   * POST handling
   *
   * @param design - the fields in the form are bound to properties of a Taco object
   *               @Valid - does validation according to the rules in class Taco
   * @param errors
   */

  @PostMapping
  public String processDesign(
      @Valid /*@ModelAttribute("design")*/ Taco design, Errors errors,
      @ModelAttribute Order order // 3. Spring MVC shouldn’t attempt to bind request parameters to it.
                                  // this order lives in the session and not saved to db until everything is OK
  ) {
    if (errors.hasErrors()) {
      return "design"; // but with errors and model again by default, so just render but with another values
    }

    // Save the taco design...
    // We'll do this in chapter 3
    log.info("Processing design: " + design);

    // everything is ok. redirecting to another view
    return "redirect:/orders/current"; // redirect view
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients
        .stream()
        .filter(x -> x.getType().equals(type))
        .collect(Collectors.toList());
  }

}

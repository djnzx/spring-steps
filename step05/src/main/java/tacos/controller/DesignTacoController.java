package tacos.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.entity.Ingredient;
import tacos.entity.Ingredient.Type;
import tacos.entity.Order;
import tacos.entity.Taco;
import tacos.repo.IngredientRepository;
import tacos.repo.TacoRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;
  private final TacoRepository tacoRepo;

  @Autowired
  public DesignTacoController(
        IngredientRepository ingredientRepo,
        TacoRepository tacoRepo) {
    this.ingredientRepo = ingredientRepo;
    this.tacoRepo = tacoRepo;
  }

  @ModelAttribute("order")
  public Order order() {
    return new Order();
  }

  @ModelAttribute("design")
  public Taco design() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm(Model model) {
    // just fetch all ingredients from the database
    List<Ingredient> ingredients = ingredientRepo.findAllToList();
    // for each type - put appropriate list to content
    Arrays.stream(Ingredient.Type.values())
        .forEach(type ->
            // we put attributes we need to render the content
            // into the Model with appropriate keys
            // for further rendering
            model.addAttribute(
                type.toString().toLowerCase(), // WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE - in lowercase
                filterByType(ingredients, type) // List<Ingredients> filtered by type (i.id, i.name)
            )
        );
    return "design";
  }

  @PostMapping
  public String processDesign(
      Model model,
/*      @Valid @ModelAttribute("design")*/ Taco taco, Errors errors, // @Valid - imply @ModelAttribute by type
      /*@ModelAttribute("order")*/ Order order) {                    // any other @ModelAttribute - find
    /**
     * POST req came like this:
     * ingredients=FLTO&ingredients=COTO&ingredients=CHED&name=ASDFG
     *
     * String[]: ingredients
     * String: name
     */
    System.out.println("======== Model ===========");
    System.out.println(model);
    System.out.println("======== TACO ===========");
    // 1. Taco object created ""by Spring"" because there is no object Taco.        - line 47: public Taco design() {...}
    // 2. Taco object taken from the session ""by Spring"" - because we declared it - line 71: @ModelAttribute("design") Taco taco
    // 3. Taco object filled with the POST data ""by Spring"" - because of POST
    // 4. Taco object is validated ""by Spring"" because of @Valid annotation       - line 71: @Valid @ModelAttribute("design") Taco taco
    // 5. Validation errors put into Errors errors
    // Taco(id=null, name=ASDFG, createdAt=null, ingredients=[Ingredient(id=FLTO, name=Flour Tortilla, type=WRAP), Ingredient(id=COTO, name=Corn Tortilla, type=WRAP), Ingredient(id=CHED, name=Cheddar, type=CHEESE)])
    System.out.println(taco);
    System.out.println("======== ERRORS =========");
    System.out.println(errors);
    System.out.println("======== ORDER ==========");
    System.out.println(order);
    System.out.println("=========================");

    if (errors.hasErrors()) {
      return "design";
    } // return view and again.

    Taco saved = tacoRepo.save(taco);
    order.addTacoToOrder(saved);

    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients
        .stream()
        .filter(x -> x.getType().equals(type))
        .collect(Collectors.toList());
  }
}

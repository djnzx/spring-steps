package app.controller;

import app.session.LongLivingCreature;
import app.session.KeysNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ========================
 * MVC Session explanation:
 * ========================
 */
@Slf4j
@Controller
@RequestMapping("/mvcse")
/**
 * step 1.
 * declaration of our intentions:
 * we wanna have SOMETHING with that NAME in the SESSION,
 * actually, just a string, which is playing role a KEY
 * to further VALUE extraction from the SESSION
 */
@SessionAttributes(KeysNames.LongLivingObjectName)
public class MvcSessionController {

  /**
   * step 2.
   * the rule how to create the initial object in our session
   * if there is no object with that KEY
   */
  @ModelAttribute(KeysNames.LongLivingObjectName)
  public LongLivingCreature theRuleHowToCreateOurCreature() {
    return new LongLivingCreature();
  }

  /**
   * step 3.
   * injecting our object with annotation `@ModelAttribute(name)`
   * anywhere we need in our requests handling:
   * GET, POST, whatever
   */
  @GetMapping()
  public String handler_get(
      @ModelAttribute(KeysNames.LongLivingObjectName) LongLivingCreature creature
  ) {
    int value = creature.getId();
    creature.setId(value+1);
    System.err.printf("GET Req came, value:%d\n", value);
    // put your code here ...
    return "empty";
  }

  @PostMapping()
  public String handler_post(
      @ModelAttribute(KeysNames.LongLivingObjectName) LongLivingCreature creature
  ) {
    int value = creature.getId();
    creature.setId(value+1);
    System.err.printf("POST Req came, value:%d\n", value);
    // put your code here ...
    return "empty";
  }
}

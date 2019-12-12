package app.controller;

import app.duringsession.LongLastingCreature;
import app.duringsession.SeObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ====================
 * Session explanation:
 * ====================
 */
@Slf4j
@Controller
@RequestMapping("/se")
/**
 * step 1.
 * declaration of our intentions:
 * we wanna have SOMETHING with that NAME in the SESSION,
 * actually, just a string, which is playing role a KEY
 * to further VALUE extraction from the SESSION
 */
@SessionAttributes(SeObj.LongLivingObjectName) // variable names, actually String[], but spring can handle it as a String
public class SessionExplanationController {

  /**
   * step 2.
   * the rule how to create the initial object in our session
   * if there is no object with that KEY
   */
  @ModelAttribute(SeObj.LongLivingObjectName)
  public LongLastingCreature theRuleHowToCreateOurCreature() {
    return new LongLastingCreature();
  }

  /**
   * step 3.
   * injecting our object with annotation `@ModelAttribute`
   * anywhere we need in our request handling
   */
  @GetMapping
  public String process_get(
      @ModelAttribute(SeObj.LongLivingObjectName) LongLastingCreature creature
  ) {
    System.err.printf("GET Req, value:%d\n", creature.getId());
    creature.setId(creature.getId()+1);
    return "session_explanation";
  }

  // step 3. and we want to have access to that inside the POST method
  @PostMapping
  public String process_post(
      @ModelAttribute(SeObj.LongLivingObjectName) LongLastingCreature creature
  ) {
    System.err.printf("POST Req, value:%d\n", creature.getId());
    creature.setId(creature.getId()+1);
    return "session_explanation";
  }
}

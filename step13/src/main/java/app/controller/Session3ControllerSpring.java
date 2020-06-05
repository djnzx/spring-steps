package app.controller;

import app.session.LongLivingCreature;
import app.session.SKeys;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

/**
 * =============================
 * Session support by Spring MVC
 * =============================
 * the most serious difference related to SessionController
 * we split the responsibility CREATE OBJECT and GET OBJECT
 * we ALWAYS have object
 * we GOT RID of `if` statement in our code.
 * we MADE it far more readable
 * if there is no object in the session. it will be created
 * by method marked with annotation `@ModelAttribute`
 */
@RestController
@RequestMapping("/ses3")
/**
 * step 1.
 * declaration of our intentions:
 * we wanna have SOMETHING with that NAME in the SESSION,
 * actually, just a string, which is playing role a KEY
 * to further VALUE extraction from the SESSION
 */
@SessionAttributes(SKeys.NAME1)
public class Session3ControllerSpring {

  /**
   * step 2.
   * the rule how to create the initial object in our session
   * if there is no object with that KEY
   */
  @ModelAttribute(SKeys.NAME1)
  public LongLivingCreature theRuleHowToCreateOurCreature(HttpSession session) {
    System.out.println("NULL. creating");
    return new LongLivingCreature(session.getId());
  }

  /**
   * step 3.
   * injecting our object with annotation `@ModelAttribute(name)`
   * anywhere we need in our requests handling:
   * GET, POST, whatever
   */
  @GetMapping()
  public String handler_get(
      @ModelAttribute(SKeys.NAME1) LongLivingCreature llc,
      HttpSession session
  ) {
    int was = llc.getValue();
    llc.inc();

    String msg = String.format("Session %s: I did identify you, value was: %d, is:%s", session.getId(), was, llc.getValue());
    System.out.println(msg);
    return msg;
  }

  /**
   * when we use Spring Session management
   * no way to invalidate session.
   * we need to call SessionStatus.setComplete()
   * and setComplete doesn't change session, it just remove all objects from it.
   */
  @GetMapping("/invalidate")
  public String invalidate(HttpSession session, SessionStatus ss) {
    String sid = session.getId();
    ss.setComplete();

    String msg = String.format("Session %s: cleaned (all objects removed)", sid);
    System.out.println(msg);
    return msg;
  }

}

package app.controller;

import app.session.KeysNames;
import app.session.LongLivingCreature;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * ==================
 * Session in general
 * explanation
 * ==================
 */
@RestController
@RequestMapping("/sege")
public class SessionControllerGeneral {

  @GetMapping
  public String response_get(HttpSession session) {

    LongLivingCreature llc =
        (LongLivingCreature) session.getAttribute(KeysNames.LongLivingObjectName);

    if (llc == null) {
      llc = new LongLivingCreature();
      llc.newUser();
    }

    System.out.printf("I did identify you:%d\n", llc.getId());
    return "GET";
  }

  @GetMapping("/inv1")
  public String handle_inv1(HttpSession session) {
    session.removeAttribute(KeysNames.LongLivingObjectName);
    return String.format("Variable with key '%s' invalidated", KeysNames.LongLivingObjectName);
  }

  @GetMapping("/invalidate")
  public String invalidate(HttpSession session) {
    session.invalidate();
    return "All variables in this session invalidated";
  }

}

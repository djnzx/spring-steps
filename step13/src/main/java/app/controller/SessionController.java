package app.controller;

import app.session.SKeys;
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
@RequestMapping("/ses")
public class SessionController {

  @GetMapping
  public String response_get(HttpSession session) {
    String sid = session.getId();

    /**
     * get the object from session
     */
    LongLivingCreature llc = (LongLivingCreature) session.getAttribute(SKeys.NAME1);
    /**
     * if there is no object - create new one
     */
    if (llc == null) {
      System.out.println("NULL. creating");
      llc = new LongLivingCreature(sid);
      session.setAttribute(SKeys.NAME1, llc);
    }

    int was = llc.getValue();
    llc.inc();

    String msg = String.format("Session %s: I did identify you, value was: %d, is:%s", sid, was, llc.getValue());
    System.out.println(msg);
    return msg;
  }

  @GetMapping("/rm_one")
  public String handle_inv1(HttpSession session) {
    session.removeAttribute(SKeys.NAME1);

    String msg = String.format("Session %s: Variable with key '%s' removed", session.getId(), SKeys.NAME1);
    System.out.println(msg);
    return msg;
  }

  @GetMapping("/invalidate")
  public String invalidate(HttpSession session) {
    session.invalidate();

    String msg = String.format("Session %s: invalidated", session.getId());
    System.out.println(msg);
    return msg;
  }

}

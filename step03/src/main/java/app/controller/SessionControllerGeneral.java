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

  boolean s_contains_attr(HttpSession session, String attr_name) {
    return session.getAttribute(attr_name) != null;
  }

  <T> T s_get_attr(HttpSession session, String attr_name, Class<T> clazz) {
    return (T) session.getAttribute(attr_name);
  }

  @GetMapping
  public String response_get(HttpSession session) {
    if (!s_contains_attr(session, KeysNames.LongLivingObjectName)) {
      session.setAttribute(KeysNames.LongLivingObjectName, new LongLivingCreature());
      System.err.println("GET Req came. There was no session. creating");
    } else {
      LongLivingCreature creature = s_get_attr(session, KeysNames.LongLivingObjectName, LongLivingCreature.class);
      int value = creature.getId();
      creature.setId(value+1);
      System.err.printf("GET Req came, value:%d\n", value);
    }
    return "GET";
  }

  @PostMapping
  public String response_post(HttpSession session) {
    if (!s_contains_attr(session, KeysNames.LongLivingObjectName)) {
      session.setAttribute(KeysNames.LongLivingObjectName, new LongLivingCreature());
      System.err.println("POST Req came. There was no session. creating");
    } else {
      LongLivingCreature creature = s_get_attr(session, KeysNames.LongLivingObjectName, LongLivingCreature.class);
      int value = creature.getId();
      creature.setId(value + 1);
      System.err.printf("POST Req came, value:%d\n", value);
    }
    return "POST";
  }

}

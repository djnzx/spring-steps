package app.controller;

import app.session.KeysNames;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serest")
@SessionAttributes(KeysNames.LongLivingObjectName)
public class SessionControllerRest {

  @GetMapping
  public String handle_get() {
    return "GET";
  }

  @PostMapping
  public String handle_post() {
    return "POST";
  }
}

package tacos.min;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class MinimalPosController {

  @GetMapping
  public String handle_get() {
    return "minimal";
  }

  @PostMapping
  public String handle_post(LoginFormData loginForm) {
    System.out.printf("FORM: %s\n", loginForm);
    return "minimal";
  }
}

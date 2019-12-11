package tacos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginLogoutController {

  @RequestMapping("/login7")
  public String handle_login() {
    return "login42";
  }

  @RequestMapping("/logout-success")
  public String handle_logout() {
    return "redirect:/";
  }
}

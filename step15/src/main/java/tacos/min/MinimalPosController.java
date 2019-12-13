package tacos.min;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/post")
public class MinimalPosController {

  @GetMapping
  public String handle_get() {
    return "minimal";
  }

//  @PostMapping
  public String handle_post_v1(@RequestParam("user_name") String user_name,
                               @RequestParam("user_password") String user_password) {
    System.out.printf("login: %s\n", user_name);
    System.out.printf("paswd: %s\n", user_password);

    return "minimal";
  }

//  @PostMapping
  public String handle_post_v2(@RequestParam String user_name,
                               @RequestParam String user_password) {
    System.out.printf("login: %s\n", user_name);
    System.out.printf("paswd: %s\n", user_password);

    return "minimal";
  }

  @PostMapping
  public String handle_post_v3(LoginFormData loginForm) {
    System.out.printf("FORM: %s\n", loginForm);
    return "minimal";
  }
//  @PostMapping
  public String handle_post(HttpServletRequest req, HttpServletResponse res) {
    return "minimal";
  }
}

package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/links")
@Controller
public class RedirectBasicsController {

  /**
   * redirect
   * http://localhost:8080/links/a
   * to:
   * http://localhost:8080/book
   *
   * generic approach
   */
  @GetMapping("a")
  public String handle_a() {
    return "redirect:/book";
  }

  /**
   * redirect
   * http://localhost:8080/links/b
   * to:
   * http://localhost:8080/book
   *
   * using RedirectView
   */
  @GetMapping("b")
  public RedirectView handle_b() {
    return new RedirectView("/book");
  }

}

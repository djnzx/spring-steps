package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@RequestMapping("/linksx")
@Controller
public class ForwardController {

  /**
   * basic forward
   * http://localhost:8080/linksx/a
   * to:
   * http://localhost:8080/book
   */
  @GetMapping("/a")
  public ModelAndView handle_a(ModelMap model) {
    return new ModelAndView("forward:/book", model);
  }

  /**
   * forward with attribute
   * http://localhost:8080/linksx/b
   * http://localhost:8080/linksx/c
   * to:
   * http://localhost:8080/book/y
   * http://localhost:8080/book/z
   * TODO doesn't work :(
   */
  @GetMapping("/b")
  public ModelAndView handle_b(ModelMap model) {
    model.addAttribute("author", "Wrath");
    return new ModelAndView("forward:/book/y", model);
  }

  @GetMapping("/c")
  public ModelAndView handle_c(RedirectAttributesModelMap model) {
    model.addAttribute("author", "Wrath");
    return new ModelAndView("forward:/book/z", model);
  }

}

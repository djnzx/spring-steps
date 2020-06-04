package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/linksx")
@Controller
public class ForwardController {

  /**
   * http://localhost:8080/linksx/a
   * to:
   * http://localhost:8080/book
   */
  @GetMapping("/a")
  public ModelAndView redirectWithUsingForwardPrefix(ModelMap model) {
    model.addAttribute("a", "6");
    return new ModelAndView("forward:/book", model);
  }

}

package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * https://www.baeldung.com/spring-redirect-and-forward
 * https://spring.io/guides/gs/uploading-files/
 */
@RequestMapping("/links")
@Controller
public class RedirectGetAdv1Controller {

  /**
   * redirect with parameters
   *
   * http://localhost:8080/links/c
   * to:
   * http://localhost:8080/book?a=3
   *
   * using RedirectView
   * with request parameter (addAttribute)
   */
  @GetMapping("c")
  public RedirectView handle3(RedirectAttributes ra) {
    ra.addAttribute("a", "3");
    return new RedirectView("/book");
  }

  /**
   * redirect with parameters
   *
   * http://localhost:8080/links/d
   * to:
   * http://localhost:8080/book?a=5
   *
   * model and view approach
   */
  @GetMapping("d")
  public ModelAndView handle6(ModelMap m) {
    m.addAttribute("a", "5"); // param
    return new ModelAndView("redirect:/book", m);
  }
}

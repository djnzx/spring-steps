package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

/**
 * passing data through the session
 */
@RequestMapping("/links")
@Controller
public class RedirectGetAdv2Controller {

  /**
   * http://localhost:8080/links/e
   * to:
   * http://localhost:8080/book/x
   *
   * using HttpSession
   * .setAttribute
   */
  @GetMapping("e")
  public RedirectView handle7(HttpSession session) {
    session.setAttribute("author", "MarioX");
    return new RedirectView("/book/x");
  }

  /**
   * http://localhost:8080/links/f
   * to:
   * http://localhost:8080/book/y
   *
   * using RedirectView
   * + addFlashAttribute (actually sets value in the session)
   */
  @GetMapping("f")
  public RedirectView handle4(RedirectAttributes ra) {
    ra.addFlashAttribute("author", "JosephY");
    return new RedirectView("/book/y");
  }

  /**
   * http://localhost:8080/links/g
   * to:
   * http://localhost:8080/book/z
   *
   *
   * using RedirectView
   * + addFlashAttribute (actually sets value in the session)
   */
  @GetMapping("g")
  public RedirectView handle5(RedirectAttributes ra) {
    ra.addFlashAttribute("author", "CyrillZ");
    return new RedirectView("/book/z");
  }

  /**
   * http://localhost:8080/links/h
   * to:
   * http://localhost:8080/book/w
   *
   *
   * using RedirectView
   * + addFlashAttribute (actually sets value in the session)
   */
  @GetMapping("h")
  public RedirectView handle6(RedirectAttributes ra) {
    ra.addFlashAttribute("author", "MarioW");
    return new RedirectView("/book/w");
  }

  /**
   * http://localhost:8080/links/i
   * to:
   * http://localhost:8080/book/w
   *
   * using RedirectView
   * + addFlashAttribute (actually sets value in the session)
   */
  @GetMapping("i")
  public ModelAndView handle6(ModelMap modelMap) {
    modelMap.addAttribute("author", "MarioW");
    return new ModelAndView("redirect:/book/w", modelMap);
  }

}

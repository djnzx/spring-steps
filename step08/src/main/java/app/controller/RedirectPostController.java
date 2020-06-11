package app.controller;

import app.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/payment")
@Controller
public class RedirectPostController {

  /**
   * POST:
   * http://localhost:8080/payment/a
   * to:
   * http://localhost:8080/payment/handle
   *
   * with body
   */
  @PostMapping("a")
  public ModelAndView redirectPost(HttpServletRequest rq) {
    // need to setup this, else you will GET redirect
    rq.setAttribute(
        View.RESPONSE_STATUS_ATTRIBUTE,
        HttpStatus.TEMPORARY_REDIRECT);
    return new ModelAndView("redirect:/payment/handle");
  }

  @ResponseBody
  @PostMapping("handle")
  public Book handle(@RequestParam String name, @RequestParam String author) {
    return new Book(name, author);
  }


}

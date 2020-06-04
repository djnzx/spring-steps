package app.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * https://www.baeldung.com/spring-boot-custom-error-page
 */
@Controller
public class MyErrorController implements ErrorController {

  @RequestMapping("/error")
  public String handle(HttpServletRequest request) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//    if (status != null) {
//      switch (Integer.parseInt(status.toString())) {
//        case HttpStatus.NOT_FOUND.value(): return "error-404";
//        case HttpStatus.INTERNAL_SERVER_ERROR.value(): return "error-500" ;
//        default: return "redirect:/book";
//      }
    return "redirect:/book";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}

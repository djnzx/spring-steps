package app.controller;

import app.entity.Person;
import app.ex.DataIntegrityViolationException;
import app.ex.OrderNotFoundException;
import app.ex.PersonNotFoundException;
import app.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 * https://www.baeldung.com/exception-handling-for-rest-with-spring
 * https://www.baeldung.com/spring-boot-custom-error-page
 */
@Log4j2
@RestController
@RequestMapping("/")
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * http://localhost:8080
   */
  @GetMapping
  public Person handle1() {
    return personService.get1();
  }

  /**
   * http://localhost:8080/2
   */
  @GetMapping("/2")
  public Person handle2() {
    return personService.get2();
  }

  /**
   * http://localhost:8080/3
   * custom handled
   */
  @GetMapping("/3")
  public Person handle3() {
    return personService.get3();
  }

  /**
   * http://localhost:8080/4
   * custom handled
   */
  @GetMapping("/4")
  public Person handle4() {
    throw new DataIntegrityViolationException();
  }

  /**
   * 1. controller level approach
   */
  /**
   * 1.1 Handle + provide custom View (HTML-page)
   * http://localhost:8080/2
   */
  @ExceptionHandler({ PersonNotFoundException.class})
  public String handlePersonNotFoundException() {
    // log
    return "personNotFoundView";
  }

  /**
   * 1.2 Convert a predefined exception to an HTTP Status code
   * http://localhost:8080/4
   */
  @ResponseStatus(value = HttpStatus.CONFLICT,
      reason="Data integrity violation")  // 409
  @ExceptionHandler(DataIntegrityViolationException.class)
  public void conflict() {
    // Nothing to do
  }

  /**
   * 1.3 full control
   * http://localhost:8080/3
   *
   * flexible: can inject HttpServletRequest, HttpServletResponse, HttpSession and/or Principal
   */
  @ExceptionHandler({ OrderNotFoundException.class})
  public ModelAndView handleOrderNotFoundException(HttpServletRequest req, Exception ex) {
    log.error("Request: " + req.getRequestURL() + " raised " + ex);

    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", ex);
    mav.addObject("url", req.getRequestURL());
    mav.setViewName("error");
    return mav;
  }
}

package app.controller;

import app.entity.sec.XUserDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/order")
public class OrderController {

  /**
   * http://localhost:8080/order/new
   */
  @GetMapping("/new")
  public String handle1(Authentication auth) {
    Object principal = auth.getPrincipal();
    return principal.toString();
  }

  /**
   * http://localhost:8080/order/new2
   */
  @GetMapping("/new2")
  public String handle2(Authentication auth) {
    XUserDetails principal = (XUserDetails) auth.getPrincipal();
    log.info(principal.getId());
    log.info(principal.getUsername());
    log.info(principal.getPassword());
    return principal.toString();
  }

}

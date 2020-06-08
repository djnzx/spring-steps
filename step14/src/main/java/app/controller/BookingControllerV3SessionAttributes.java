package app.controller;

import app.session.CustomerDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Log4j2
@Controller
@RequestMapping("/v3")
/**
 * that the mark,
 * wee need to `retain` this attribute between requests
 */
@SessionAttributes(
    names = { CustomerDetails.ATTR },
    types = { CustomerDetails.class})
public class BookingControllerV3SessionAttributes {

  private static String fmt(String f, Object... as) {
    return String.format(f, as);
  }

  /**
   * that the rule how to create the initial value for our attribute
   */
  @ModelAttribute(CustomerDetails.ATTR)
  public CustomerDetails create(HttpSession session) {
    log.info(fmt("Creating new object CustomerDetails for session %s", session.getId()));
    return new CustomerDetails(session.getId());
  }

  /**
   * http://localhost:8080/booking
   */
  @GetMapping("booking")
  public String handle_booking_get(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd,
      Model m
  ) {
    log.info(fmt("GET -> /booking: %s", cd));
    m.addAttribute("seat", cd.getSeat());
    return "1booking";
  }

  @PostMapping("booking")
  public RedirectView handle_booking_post(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info(fmt("POST -> /booking: %s", cd));
    return new RedirectView("customer");
  }

  /**
   * http://localhost:8080/customer
   */
  @GetMapping("customer")
  public String handle_customer_get(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd,
      Model m
  ) {
    log.info(fmt("GET -> /customer: %s", cd));
    m.addAttribute("firstname", cd.getFirstname());
    m.addAttribute("lastname", cd.getLastname());
    return "2customer";
  }

  @PostMapping("customer")
  public RedirectView handle_customer_post(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info(fmt("POST -> /customer: %s", cd));
    return new RedirectView("payment");
  }

  /**
   * http://localhost:8080/payment
   */
  @GetMapping("payment")
  public String handle_payment_get(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd,
      Model m
  ) {
    log.info(fmt("GET -> /payment: %s", cd));
    m.addAttribute("cardno", cd.getCardno());
    return "3payment";
  }

  @PostMapping("payment")
  public RedirectView handle_payment_post(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info(fmt("POST -> /payment: %s", cd));
    return new RedirectView("confirm");
  }

  /**
   * http://localhost:8080/confirm
   */
  @GetMapping("confirm")
  public String handle_confirm_get(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd,
      Model m
  ) {
    log.info(fmt("GET -> /confirm: %s", cd));
    m.addAttribute("seat", cd.getSeat());
    m.addAttribute("firstname", cd.getFirstname());
    m.addAttribute("lastname", cd.getLastname());
    m.addAttribute("cardno", cd.getCardno());
    return "4confirm";
  }
}

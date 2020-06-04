package app.controller;

import app.forms.FormBooking;
import app.forms.FormCustomer;
import app.forms.FormPayment;
import app.session.CustomerDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Log4j2
@Controller
@RequestMapping("/")
@SessionAttributes(names = { CustomerDetails.ATTR_NAME }, types = CustomerDetails.class)
public class AppController {

  private static String fmt(String f, Object... as) {
    return String.format(f, as);
  }

  @ModelAttribute(CustomerDetails.ATTR_NAME)
  public CustomerDetails create(HttpSession session) {
    System.out.println("NULL. creating");
    return new CustomerDetails(session.getId());
  }

  /**
   * http://localhost:8080/booking
   */
  @GetMapping("booking")
  public String handle_booking_get(
    @ModelAttribute(CustomerDetails.ATTR_NAME) CustomerDetails cd
      , Model m
  ) {
    log.info(fmt("GET -> /booking: %s", cd));
    m.addAttribute("seat", cd.getSeat());
    return "1booking";
  }

  @PostMapping("booking")
  public RedirectView handle_booking_post(FormBooking form, RedirectAttributes attr
    , @ModelAttribute(CustomerDetails.ATTR_NAME) CustomerDetails cd
  ) {
    log.info(fmt("POST -> /booking: %s", cd));
    log.info(form);
    return new RedirectView("customer");
  }

  /**
   * http://localhost:8080/customer
   */
  @GetMapping("customer")
  public String handle_customer_get(
      @ModelAttribute(CustomerDetails.ATTR_NAME) CustomerDetails cd
      , Model m
  ) {
    log.info(fmt("GET -> /customer: %s", cd));
    m.addAttribute("firstname", cd.getFirstname());
    m.addAttribute("lastname", cd.getLastname());
    return "2customer";
  }

  @PostMapping("customer")
  public RedirectView handle_customer_post(FormCustomer form, RedirectAttributes attr
      , @ModelAttribute(CustomerDetails.ATTR_NAME) CustomerDetails cd
  ) {
    log.info(fmt("POST -> /customer: %s", cd));
    log.info(form);
    return new RedirectView("payment");
  }

  /**
   * http://localhost:8080/payment
   */
  @GetMapping("payment")
  public String handle_payment_get(
      @ModelAttribute(CustomerDetails.ATTR_NAME) CustomerDetails cd
      , Model m
  ) {
    log.info(fmt("GET -> /payment: %s", cd));
    m.addAttribute("cardno", cd.getCardno());
    return "3payment";
  }

  @PostMapping("payment")
  public RedirectView handle_payment_post(FormPayment form, RedirectAttributes attr
      , @ModelAttribute(CustomerDetails.ATTR_NAME) CustomerDetails cd
  ) {
    log.info(fmt("POST -> /payment: %s", cd));
    log.info(form);
    return new RedirectView("confirm");
  }

  /**
   * http://localhost:8080/confirm
   */
  @GetMapping("confirm")
  public String handle_confirm_get(SessionStatus status
      , @ModelAttribute(CustomerDetails.ATTR_NAME) CustomerDetails cd
                                   , Model m
  ) {
    log.info(fmt("GET -> /confirm: %s", cd));
//    status.setComplete();
    m.addAttribute("seat", cd.getSeat());
    m.addAttribute("firstname", cd.getFirstname());
    m.addAttribute("lastname", cd.getLastname());
    m.addAttribute("cardno", cd.getCardno());
    return "4confirm";
  }
}

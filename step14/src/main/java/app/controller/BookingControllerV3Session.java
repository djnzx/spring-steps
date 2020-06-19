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
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequestMapping("/v3")
@SessionAttributes(
    names = { CustomerDetails.ATTR },
    types = { CustomerDetails.class }
)
public class BookingControllerV3Session {

  private static String pf(String fmt, Object... a) {
    return String.format(fmt, a);
  }

  // how to create our attribute
  @ModelAttribute(CustomerDetails.ATTR)
  public CustomerDetails create0000() {
    return new CustomerDetails();
  }

  /**
   * http://localhost:8080/booking
   */
  @GetMapping("booking")
  public String handle_booking_get(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd,
      Model m
  ) {
    log.info(pf("GET  -> /booking: %s", cd));
    m.addAttribute("seat", cd.getSeat());
    return "1booking";
  }

  @PostMapping("booking")
  public RedirectView handle_booking_post(
      FormBooking form,
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info(pf("POST -> /booking: %s", form));
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
    log.info(pf("GET  -> /customer: %s", cd));
    m.addAttribute("firstname", cd.getFirstname());
    m.addAttribute("lastname", cd.getLastname());
    return "2customer";
  }

  @PostMapping("customer")
  public RedirectView handle_customer_post(
      FormCustomer form,
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info(pf("POST -> /customer: %s", form));
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
    log.info(pf("GET  -> /payment: %s", cd));
    m.addAttribute("cardno", cd.getCardno());
    return "3payment";
  }

  @PostMapping("payment")
  public RedirectView handle_payment_post(
      FormPayment form,
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info(pf("POST -> /payment: %s", form));
    return new RedirectView("review");
  }

  /**
   * http://localhost:8080/review
   */
  @GetMapping("review")
  public String handle_review_get(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd,
      Model m
  ) {
    log.info(pf("GET  -> /review: %s", cd));
    m.addAttribute("seat", cd.getSeat());
    m.addAttribute("firstname", cd.getFirstname());
    m.addAttribute("lastname", cd.getLastname());
    m.addAttribute("cardno", cd.getCardno());
    return "4review";
  }

  @PostMapping("review")
  public RedirectView handle_review_post(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info("POST -> /review");
    return new RedirectView("confirm");
  }

  /**
   * http://localhost:8080/confirm
   */
  @GetMapping("confirm")
  public String handle_confirm_get(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd,
      Model m,
      SessionStatus status
  ) {
    log.info(pf("GET  -> /confirm: %s", cd));
    status.setComplete();
    m.addAttribute("seat", cd.getSeat());
    m.addAttribute("firstname", cd.getFirstname());
    m.addAttribute("lastname", cd.getLastname());
    m.addAttribute("cardno", cd.getCardno());
    return "5confirm";
  }

}

package app.controller;

import app.session.CustomerDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequestMapping("/")
@SessionAttributes(
    names = { CustomerDetails.ATTR },
    types = { CustomerDetails.class }
)
public class BookingControllerV4Final {

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
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info("GET  -> /booking");
    return "v4/1booking";
  }

  @PostMapping("booking")
  public RedirectView handle_booking_post(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info(pf("POST -> /booking: %s", cd));
    return new RedirectView("customer");
  }

  /**
   * http://localhost:8080/customer
   */
  @GetMapping("customer")
  public String handle_customer_get(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info("GET  -> /customer");
    return "v4/2customer";
  }

  @PostMapping("customer")
  public RedirectView handle_customer_post(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info(pf("POST -> /customer: %s", cd));
    return new RedirectView("payment");
  }

  /**
   * http://localhost:8080/payment
   */
  @GetMapping("payment")
  public String handle_payment_get(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info("GET  -> /payment");
    return "v4/3payment";
  }

  @PostMapping("payment")
  public RedirectView handle_payment_post(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info(pf("POST -> /payment: %s", cd));
    return new RedirectView("review");
  }

  /**
   * http://localhost:8080/review
   */
  @GetMapping("review")
  public String handle_review_get(
      @ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd
  ) {
    log.info("GET  -> /review");
    return "v4/4review";
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
      SessionStatus status
  ) {
    log.info("GET  -> /confirm");
    status.setComplete();
    return "v4/5confirm";
  }

}

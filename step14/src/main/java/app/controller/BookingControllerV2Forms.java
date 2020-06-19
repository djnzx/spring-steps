package app.controller;

import app.forms.FormBooking;
import app.forms.FormCustomer;
import app.forms.FormPayment;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequestMapping("/v2")
public class BookingControllerV2Forms {

  private static String pf(String fmt, Object... a) {
    return String.format(fmt, a);
  }

  /**
   * http://localhost:8080/booking
   */
  @GetMapping("booking")
  public String handle_booking_get() {
    log.info("GET  -> /booking");
    return "1booking";
  }

  @PostMapping("booking")
  public RedirectView handle_booking_post(FormBooking form) {
    log.info(pf("POST -> /booking: %s", form));
    return new RedirectView("customer");
  }

  /**
   * http://localhost:8080/customer
   */
  @GetMapping("customer")
  public String handle_customer_get() {
    log.info("GET  -> /customer");
    return "2customer";
  }

  @PostMapping("customer")
  public RedirectView handle_customer_post(FormCustomer form) {
    log.info(pf("POST -> /customer: %s", form));
    return new RedirectView("payment");
  }

  /**
   * http://localhost:8080/payment
   */
  @GetMapping("payment")
  public String handle_payment_get() {
    log.info("GET  -> /payment");
    return "3payment";
  }

  @PostMapping("payment")
  public RedirectView handle_payment_post(FormPayment form) {
    log.info(pf("POST -> /payment: %s", form));
    return new RedirectView("review");
  }

  /**
   * http://localhost:8080/review
   */
  @GetMapping("review")
  public String handle_review_get() {
    log.info("GET  -> /review");
    return "4review";
  }

  @PostMapping("review")
  public RedirectView handle_review_post() {
    log.info("POST -> /review");
    return new RedirectView("confirm");
  }

  /**
   * http://localhost:8080/confirm
   */
  @GetMapping("confirm")
  public String handle_confirm_get() {
    log.info("GET  -> /confirm");
    return "5confirm";
  }

}

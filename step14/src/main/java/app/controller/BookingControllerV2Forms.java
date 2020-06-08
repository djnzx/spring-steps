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
@RequestMapping("/v2")
public class BookingControllerV2Forms {

  private static String fmt(String f, Object... as) {
    return String.format(f, as);
  }

  /**
   * http://localhost:8080/booking
   */
  @GetMapping("booking")
  public String handle_booking_get() {
    log.info("GET -> /booking");
    return "1booking";
  }

  @PostMapping("booking")
  public RedirectView handle_booking_post(FormBooking form) {
    log.info(fmt("POST -> /booking: %s", form));
    return new RedirectView("customer");
  }

  /**
   * http://localhost:8080/customer
   */
  @GetMapping("customer")
  public String handle_customer_get() {
    log.info("GET -> /customer");
    return "2customer";
  }

  @PostMapping("customer")
  public RedirectView handle_customer_post(FormCustomer form) {
    log.info(fmt("POST -> /customer: %s", form));
    return new RedirectView("payment");
  }

  /**
   * http://localhost:8080/payment
   */
  @GetMapping("payment")
  public String handle_payment_get() {
    log.info("GET -> /payment");
    return "3payment";
  }

  @PostMapping("payment")
  public RedirectView handle_payment_post(FormPayment form) {
    log.info(fmt("POST -> /payment: %s", form));
    return new RedirectView("confirm");
  }

  /**
   * http://localhost:8080/confirm
   */
  @GetMapping("confirm")
  public String handle_confirm_get() {
    log.info("GET -> /confirm");
    return "4confirm";
  }
}

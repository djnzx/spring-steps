package app.controller;

import app.forms.FormData;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/form")
public class FormController {

  private static String fmt(String format, Object... args) {
    return String.format(format, args);
  }

  /**
   * http://localhost:8080/form
   */
  @GetMapping
  public String handle_GETa() {
    log.info("GET  -> /form");
    return "form_a";
  }

  @PostMapping
  public String handle_POSTa1(@RequestParam String x, @RequestParam String y) {
    log.info(fmt("POST -> /form x=%s", x));
    log.info(fmt("POST -> /form y=%s", y));
    return "form_a";
  }

  /**
   * http://localhost:8080/form/b
   */
  @GetMapping("b")
  public String handle_GETb() {
    log.info("GET  -> /form/b");
    return "form_b";
  }

  @PostMapping("process_b")
  public String handle_POSTb(@RequestParam String x, @RequestParam String y) {
    log.info(fmt("POST -> /form/process_b x=%s", x));
    log.info(fmt("POST -> /form/process_b y=%s", y));
    return "form_b";
  }

  /**
   * http://localhost:8080/form/c
   */
  @GetMapping("c")
  public String handle_GETc(Model model) {
    log.info("GET  -> /form/c");
    model.addAttribute("handler", "process_c");
    return "form_c";
  }

  @PostMapping(path = "process_c")
  @ResponseBody
  public String handle_POSTc(FormData form) {
    String msg = fmt("POST -> /form/process_c form=%s", form);
    log.info(msg);
    return msg;
  }

}

package app.controller;

import app.service.Formatter;
import app.service.Formatter1;
import app.service.Formatter2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fmt")
public class PrintController {

  private final Formatter1 formatter1;
  private final Formatter2 formatter2;
  private final Formatter formatter_primary;
  private final Formatter formatter_qualifier;

  public PrintController(
      Formatter1 formatter1, // no problem, we inject by type
      Formatter2 formatter2, // no problem, we inject by type
//      Printer printer, // here is a problem, if no implementations marked `@Primary`
      Formatter formatter_primary, // now Primary set on 1st implementatio
      @Qualifier("formatter_V2") Formatter formatter_qualifier // here we inject Formatter2 despite Formatter1 marked as a Primary
  ) {
    this.formatter1 = formatter1;
    this.formatter2 = formatter2;
    this.formatter_primary = formatter_primary;
    this.formatter_qualifier = formatter_qualifier;
  }

  @GetMapping("/f_1")
  public String handle_1() {
    return formatter1.format("1st implementation because by type=Formatter1");
  }

  @GetMapping("/f_2")
  public String handle_2() {
    return formatter2.format("2nd implementation because by type=Formatter2");
  }

  @GetMapping("/f_prim")
  public String handle_3() {
    return formatter_primary.format("1st implementation because it is marked by @Primary");
  }

  @GetMapping("/f_qual")
  public String handle_4() {
    return formatter_qualifier.format("2nd implementation because we used @Qualifier in the constructor");
  }

}

package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class HomeController {

//  @GetMapping("/")
  public String home_handler() {
    /**
     * How that view is implemented depends on a few factors,
     * but because Thymeleaf is in your classpath, you can define that template with Thymeleaf.
     *
     */
    return "home"; // view name: must me located in "/resources/templates"+home+".html"
  }

}

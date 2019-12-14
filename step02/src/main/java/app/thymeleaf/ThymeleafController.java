package app.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ThymeleafController {

  @RequestMapping("/tl")
  public String handle(Model model) {
    List<Product> products = Arrays.asList(
        new Product("Iphone 5s", 499.999),
        new Product("MacBook", 1600.3));

    Headers headers = new Headers("Item#name", "Item#price");

    model.addAttribute("products", products);
    model.addAttribute("headers", headers);

    return "thl001";
  }
}

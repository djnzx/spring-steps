package app.controller;

import app.entity.Book;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

  /**
   * http://localhost:8080/book
   */
  @GetMapping
  public Book handle_get1() {
    return new Book("Scala", "Jim");
  }

  /**
   * http://localhost:8080/book/x
   * injected attribute can be null !!!
   *
   * injecting a particular model attribute field
   */
  @GetMapping("/x")
  public Book handle_get2(@ModelAttribute("author") Object author) {
    String name = (String) author;
    return new Book("Scala", name);
  }

  /**
   * http://localhost:8080/book/y
   * injected attribute can be null !!!
   *
   * injecting a whole model
   */
  @GetMapping("/y")
  public Book handle_get3(Model model) {
    Object author = model.getAttribute("author");
    String name = (String) author;
    return new Book("Scala3", name);
  }

  @GetMapping("/z")
  public Book handle_z(ModelMap model) {
    Object author = model.getAttribute("author");
    String name = (String) author;
    return new Book("Scala4", name);
  }

}

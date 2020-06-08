package app.controller;

import app.entity.Book;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
   *
   * accessing to the attribute
   * through the Session
   * attribute on model can be null !!!
   */
  @GetMapping("/x")
  public Book handle_get_x(HttpSession session) {
    Object author = session.getAttribute("author");
    String name = (String) author;
    return new Book("Scala1", name);
  }

  /**
   * http://localhost:8080/book/y
   *
   * accessing to the attribute
   * through the Model (better abstraction)
   * attribute on model can be null !!!
   */
  @GetMapping("/y")
  public Book handle_get_y(Model model) {
    Object author = model.getAttribute("author");
    String name = (String) author;
    return new Book("Scala2", name);
  }

  /**
   * http://localhost:8080/book/z
   *
   * accessing to the attribute
   * through the ModelMap (better abstraction)
   * attribute on model can be null !!!
   */
  @GetMapping("/z")
  public Book handle_get_z(ModelMap modelMap) {
    Object author = modelMap.getAttribute("author");
    String name = (String) author;
    return new Book("Scala3", name);
  }

  /**
   * http://localhost:8080/book/w
   *
   * accessing to the particular attribute
   * through the @ModelAttribute annotation
   * attribute on model can be null !!!
   */
  @GetMapping("/w")
  public Book handle_get_w(@ModelAttribute("author") Object author) {
    String name = (String) author;
    return new Book("Scala4", name);
  }

}

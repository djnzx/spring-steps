package app.controller;

import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * http://localhost:8080/user
 */
@RestController
public class UserController {

  private final UserService service;

  public UserController(@Autowired UserService service) {
    this.service = service;
  }

  @GetMapping("/users")
  public Collection<User> get_all () {
    return service.getAll();
  }

  @GetMapping("/user") // "/user?id=1"
  public User get_one1 (@RequestParam("id") int id____) {
    return service.getOne(id____);
  }

  @GetMapping("/user/{id}") // "/user/1"
  public User get_one2 (@PathVariable("id") int id____) {
    return service.getOne(id____);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/user/add/{name}") // "/user/add/Alex"
  public User add_one1 (@PathVariable("name") String name____) {
    return service.createOne(name____);
  }

  @PostMapping("/user/add") // "/user/add?name=Alex"
  public User add_one2 (@RequestParam("name") String name____) {
    return service.createOne(name____);
  }

}

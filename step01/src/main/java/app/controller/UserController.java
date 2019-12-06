package app.controller;

import app.entity.Person;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

  private UserService userService;

  public UserController(@Autowired UserService service) {
    this.userService = service;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/users")
  public List<Person> get_all_users() {
    return userService.get();
  }

  @GetMapping("/users/add1/{id}/{name}")
  public List<Person> add1(@PathVariable("id") int id, @PathVariable("name") String name) {
    userService.add(name);
    return userService.get();
  }

  @GetMapping("/users/add2")
  public List<Person> add2(@RequestParam("name") String name) {
    userService.add(name);
    return userService.get();
  }

  @RequestMapping(method = RequestMethod.POST, path = "/users/add3/{id}/{name}")
  public List<Person> add3(@PathVariable("id") int id, @PathVariable("name") String name) {
    userService.add(name);
    return userService.get();
  }

  @RequestMapping(method = RequestMethod.POST, path = "/users/add4")
  public List<Person> add4(@RequestParam("name") String name) {
    userService.add(name);
    return userService.get();
  }

}

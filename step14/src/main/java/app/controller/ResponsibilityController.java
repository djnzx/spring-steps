package app.controller;

import app.entity.Responsibility;
import app.service.ResponsibilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/r")
public class ResponsibilityController {

  private ResponsibilityService responsibilityService;

  @Autowired
  public ResponsibilityController(ResponsibilityService responsibilityService) {
    this.responsibilityService = responsibilityService;
  }

  @GetMapping
  public Iterable<Responsibility> handle_get_all() {
    return responsibilityService.get_all();
  }

  @GetMapping("/{id}")
  public Optional<Responsibility> handle_get_1(@PathVariable("id") long id) {
    return responsibilityService.get_one(id);
  }

  @PostMapping
  public Responsibility handle_post(@RequestBody Responsibility r) {
    responsibilityService.create_one(r);
    return r;
  }

  @PostMapping("/cr")
  public Collection<Responsibility> handle_post() {
    return responsibilityService.create_initial();
  }
}

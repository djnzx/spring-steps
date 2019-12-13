package tacos.controller;

import org.springframework.web.bind.annotation.*;
import tacos.entity.Message;

@RestController
@RequestMapping("/message")
public class InstagramController {

  @GetMapping("/{id}")
  public Message handle_get(@PathVariable("id") int id) {
    return new Message(id + 1,id + 2,"Helo");
  }

  @PutMapping
  public Message handle_put(@RequestBody Message message) {
    System.out.println(message);
    return message;
  }



}

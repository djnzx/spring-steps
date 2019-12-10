package app.controller;

import app.entity.Message;
import app.service.MessageDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/msgdb")
public class MessageDbController {

  private final MessageDbService messageDbService;

  @Autowired
  public MessageDbController(MessageDbService messageDbService) {
    this.messageDbService = messageDbService;
  }

  // get all messages
  @GetMapping
  public Iterable<Message> handle_get_all() {
    return messageDbService.get_all();
  }

  // get one message (id)
  @GetMapping("/{id}")
  public Message handle_get_one(@PathVariable("id") int id) {
    return messageDbService.get_one(id);
  }

  // save one message
  @PutMapping
  public Message handle_put_one(@RequestBody Message message) {
    return messageDbService.put_one(message);
  }

  // delete one message
  @DeleteMapping("/{id}")
  public String handle_del_one(@PathVariable int id) {
    messageDbService.del_one(id);
    return String.format("message %d deleted", id);
  }

}

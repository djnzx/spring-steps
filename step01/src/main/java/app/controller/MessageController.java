package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/msg")
public class MessageController {

  @GetMapping
  public String handle_get(){
    return "hello_msg_template";
  }

  @ResponseBody
  @GetMapping("/one")
  public String handle_get_1(){
//    if (true) throw new IllegalArgumentException("I dont care");
    return "Hello World!";
  }

//  @RequestMapping(method = RequestMethod.POST)
  public String handle_post_v1(HttpServletRequest req){
    String user1 = req.getParameter("user1");
    String user2 = req.getParameter("user2");
    System.out.printf("user1: %s\n", user1);
    System.out.printf("user2: %s\n", user2);
    return "hello_msg_template";
  }

//  @RequestMapping(method = RequestMethod.POST)
  public String handle_post_v2(
      @RequestParam("user1") String user1,
      @RequestParam("user2") String user2
      ){
    System.out.printf("user1: %s\n", user1);
    System.out.printf("user2: %s\n", user2);
    return "hello_msg_template";
  }

//  @RequestMapping(method = RequestMethod.POST)
  public String handle_post_v3(
      @RequestParam String user1,
      @RequestParam String user2
      ){
    System.out.printf("user1: %s\n", user1);
    System.out.printf("user2: %s\n", user2);
    return "hello_msg_template";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String handle_post_v4(PostFormMessage formData){
    System.out.println(formData);
    return "hello_msg_template";
  }

}

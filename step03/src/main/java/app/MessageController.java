package app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
@RequestMapping("/msg")
public class MessageController {

  @ResponseBody
  /**
   * @ResponseBody - means we treat method result as a response,
   * not a view name, as by default
   * object returned from the method will be converted to JSON
   * we can use this annotation on class-level,
   * but for the String return type we must explicitly specify it
   *
   * http://localhost:8080/msg
   */
  @GetMapping()
  public String handle_get_1(){
    return "Hello World 3 !";
  }

  /**
   * getting parameters from request line
   * http://localhost:8080/msg/one?a=3&b=4&msg=Hello+from+alex
   */
  @GetMapping("/one*")
  public Message handle_get_2(
      @RequestParam("a")int u1,
      @RequestParam("b")int u2,
      @RequestParam("msg")String m
  ) {
    return new Message(u1, u2, m);
  }

  /**
   * extracting fragments fom path
   * http://localhost:8080/msg/two/5/7/Hello%20From%20Alex
   */
  @GetMapping("/two/{a}/{b}/{msg}")
  public Message handle_get_3(
      @PathVariable("a")int u1,
      @PathVariable("b")int u2,
      @PathVariable("msg")String m
  ) {
    return new Message(u1, u2, m);
  }

  /**
   * ro run any non-GET HTTP request
   * you need to use specific application, like Postman
   * 1. use endpoint as a request path "/msg/v1"
   * 2. choose the request type of POST
   * 3. put this line to request address
   * http://localhost:8080/msg/v1?a=3&b=4&msg=data+got+from+POSTv1
   *
   * that basic approach based on javax.servlet.http.HttpServletRequest
   */
  @RequestMapping(method = RequestMethod.POST, value = "/v1")
  public Message handle_post_v1(HttpServletRequest req) {
    int user1 = Integer.parseInt(req.getParameter("a"));
    int user2 = Integer.parseInt(req.getParameter("b"));
    String msg = req.getParameter("msg");
    return new Message(user1, user2, msg);
  }

  /**
   * http://localhost:8080/msg/v2?a=33&b=44&msg=data+got+from+POSTv2
   *
   * spring native approach
   */
  @PostMapping("/v2")
  public Message handle_post_v2(
      @RequestParam("a") int user1,
      @RequestParam("b") int user2,
      @RequestParam("msg") String m
  ) {
    return new Message(user1, user2, m);
  }

  /**
   * http://localhost:8080/msg/v3?a=111&b=222&msg=data+got+from+POSTv3
   *
   * spring native approach. you can skip parameter names
   * if variables names corresponding parameter names
   */
  @PostMapping("/v3")
  public Message handle_post_v3(
      @RequestParam int a,
      @RequestParam int b,
      @RequestParam String msg
  ) {
    return new Message(a, b, msg);
  }

  /**
   * http://localhost:8080/msg/v4?a=1111&b=2222&msg=data+got+from+POSTv4
   *
   * to make this approach working, you need to have:
   * 1. No-Args constructor
   * 2. setter for each field which is presented as a parameter
   *
   * spring native approach. you can use separate class
   * for handling all parameters
   * very useful and convenient when processing POST forms
   */
  @PostMapping("/v4")
  public Message handle_post_v4(AllMsgParams params) {
    return new Message(params.a, params.b, params.msg);
  }

  /**
   * consuming JSON as body
   * to make this approach working, you need to have:
   *
   * 1. No-Args constructor
   * 2. setter for each field which is presented as a parameter
   *
   * how to check:
   * 1. open Postman
   * 2. path: http://localhost:8080/msg/v5
   * 3. type: POST
   * 4. add header: Content-Type: application/json
   * 5. body(raw): {"user1": 101,"user2": 202,"content": "data got via JSON in body(v5)"}
   *
   */
  @PostMapping("/v5")
  public Message handle_post_v5(@RequestBody Message msg) {
    return msg;
  }
}

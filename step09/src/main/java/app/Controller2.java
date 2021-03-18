package app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usr")
public class Controller2 {

  @GetMapping()
  public String handle_get_1(){
    return "User !";
  }
}

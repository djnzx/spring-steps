package app.controller;

import app.service.SuperSmartCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/calc")
public class CalcController {

  private final SuperSmartCalculator ssc;

  @Autowired
  public CalcController(SuperSmartCalculator ssc) {
    this.ssc = ssc;
  }

  @GetMapping
  public String handle_get(@RequestParam int a,
                           @RequestParam int b) {
    int z = ssc.add(a, b);
    return String.valueOf(z);
  }
}

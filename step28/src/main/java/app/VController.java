package app;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * https://spring.io/guides/gs/validating-form-input/
 * https://reflectoring.io/bean-validation-with-spring-boot/
 * http://dolszewski.com/spring/custom-validation-annotation-in-spring/
 */
@RestController
@Validated
public class VController {

  @PostMapping
  public String m1(@Valid @RequestBody Form f) {
    System.out.println(f);
    return "OK";
  }

}

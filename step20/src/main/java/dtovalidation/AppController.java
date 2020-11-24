package dtovalidation;

import dtovalidation.dto.RqUserRegister;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.*;
import java.util.Set;

@RestController
public class AppController {

  // automatic validation
  @PostMapping
  public String processAutomatic(@Valid @RequestBody RqUserRegister rq) {
    System.out.println(rq);
    return "1";
  }

  // manual validation
  @PostMapping("/m")
  public String processManual(@RequestBody RqUserRegister rq) {
    System.out.println(rq);
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<RqUserRegister>> result = validator.validate(rq);
    result.forEach(cv -> System.out.println(cv.getMessage()));
    return "2";
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public void handle(MethodArgumentNotValidException x) {
    System.out.println(x.getMessage());
  }

}

package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * taken here:
 * https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc#sample-application
 *
 * https://github.com/paulc4/mvc-exceptions.git
 * https://github.com/alexr007/spring-exceptions.git
 *
 * For exceptions you write, consider adding @ResponseStatus to them.
 * For all other exceptions implement an @ExceptionHandler method on
 * a @ControllerAdvice class or use an instance of SimpleMappingExceptionResolver.
 * You may well have SimpleMappingExceptionResolver configured for your application already,
 * in which case it may be easier to add new exception classes to it than implement a @ControllerAdvice.
 * For Controller specific exception handling add @ExceptionHandler methods to your controller.
 * Warning: Be careful mixing too many of these options in the same application.
 * If the same exception can be handed in more than one way, you may not get the behavior you wanted.
 * @ExceptionHandler methods on the Controller are always selected before those on any @ControllerAdvice instance.
 * It is undefined what order controller-advices are processed.
 */
@SpringBootApplication
public class ExApp {
  public static void main(String[] args) {
    SpringApplication.run(ExApp.class, args);
  }
}

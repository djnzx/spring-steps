package app.controller;

import app.ex.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A controller advice allows you to use exactly the same exception handling techniques
 * but apply them across the _WHOLE APPLICATION_, not just to an individual controller.
 *
 * You can think of them as an annotation driven interceptor.
 */
@ControllerAdvice
public class AdviceApproachHandler {
  @ResponseStatus(HttpStatus.CONFLICT)  // 409
  @ExceptionHandler(DataIntegrityViolationException.class)
  public void handleConflict() {
    // Nothing to do
  }
}

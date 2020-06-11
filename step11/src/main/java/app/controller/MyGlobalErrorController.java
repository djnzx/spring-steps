package app.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc#sample-application
 *
 * Globally in App
 *
 * At start-up, Spring Boot tries to find a mapping for `/error`
 *
 * If no view-resolver mapping for /error can be found,
 * Spring Boot defines its own fall-back error page - the so-called “Whitelabel Error Page”
 *
 * If you are making a RESTful request (the HTTP request has specified a desired
 * response type other than HTML) Spring Boot returns a JSON representation
 * of the same error information that it puts in the “Whitelabel” error page.
 *
 * curl -H "Accept: application/json" http://localhost:8080/no-such-page
 *
 * Normally any unhandled exception thrown when processing a web-request causes the server to return an HTTP 500 response
 *
 *
 */
@Controller
public class MyGlobalErrorController implements ErrorController {

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());

      if(statusCode == HttpStatus.NOT_FOUND.value()) {
        return "error-404"; // custom web pages
      }
      else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
        return "error-500"; // custom web pages
      }
    }
    // all other errors will be caught here
    return "error";
  }
}

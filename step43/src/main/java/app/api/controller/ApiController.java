package app.api.controller;

import app.api.dto.rs.ApiMessageRs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
/**
 * /api/guest  - GET - any unregistered
 * /api/home   - GET - any authenticated
 * /api/admin  - GET - any authorized with ADMIN role
 * /api/me     - GET - any authorized with USER role
 * /api/news   - GET - any authorized with any USER or ADMIN role
 */

  @GetMapping("/guest")
  public ApiMessageRs handle_guest() {
    return new ApiMessageRs("OK:any unregistered");
  }

  @GetMapping("/home")
  public ApiMessageRs handle_home() {
    return new ApiMessageRs("OK:any authenticated");
  }

  @GetMapping("/admin")
  public ApiMessageRs handle_admin() {
    return new ApiMessageRs("OK:any authorized with ADMIN role");
  }

  @GetMapping("/me")
  public ApiMessageRs handle_user() {
    return new ApiMessageRs("OK:any authorized with USER role");
  }

  @GetMapping("/news")
  public ApiMessageRs handle_news() {
    return new ApiMessageRs("OK:any authorized with any USER or ADMIN role");
  }

}

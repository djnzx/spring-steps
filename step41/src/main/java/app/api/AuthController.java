package app.api;

import app.dto.rq.LoginRq;
import app.dto.rq.LogoutRq;
import app.dto.rq.RegisterRq;
import app.dto.rs.LoginRs;
import app.dto.rs.LogoutRs;
import app.dto.rs.RegisterRs;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api")
public class AuthController {

  @PostMapping("/login")
  public LoginRs handle_login(@RequestBody LoginRq rq) {
    log.info(rq);
    return new LoginRs("POST:/login:not implemented", "XXXYYYZZZ");
  }

  @PostMapping("/logout")
  public LogoutRs handle_logout(@RequestBody LogoutRq rq) {
    log.info(rq);
    return new LogoutRs("POST:/logout:not implemented");
  }

  @PostMapping("/register")
  public RegisterRs handle_register(@RequestBody RegisterRq rq) {
    log.info(rq);
    return new RegisterRs("POST:/logout:not implemented");
  }
}

package app.service;

import app.entity.db.XUser;
import app.repo.XUserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.function.Predicate;

@Log4j2
@Configuration
public class XUserCreationService {
  private final XUserRepo repo;
  private final PasswordEncoder enc;

  public XUserCreationService(XUserRepo repo, PasswordEncoder enc) {
    this.repo = repo;
    this.enc = enc;
  }

  public void create() {
    log.info(":: users creation...");
    repo.saveAll(Arrays.asList(
        new XUser("jimmy", enc.encode(new String("012"))),
        new XUser("jim", enc.encode(new String("123c")), "USER"),
        new XUser("john", enc.encode(new String("234c")), "ADMIN"),
        new XUser("alex", enc.encode(new String("345c")), "USER", "ADMIN")
    ));
    log.info(":: ...users creation done");
  }

  public boolean passwordIsUnique(String passwordEntered) {
    return repo.findAll().stream()
        .noneMatch(xu ->
            enc.matches(passwordEntered, xu.getPassword())
        );
  }

}

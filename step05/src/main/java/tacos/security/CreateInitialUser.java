package tacos.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CreateInitialUser {

  private final DbUserRepo repo;
  private final PasswordEncoder enc;

  public CreateInitialUser(DbUserRepo repo, PasswordEncoder enc) {
    this.repo = repo;
    this.enc = enc;
  }

  public void create() {
    DbUser dbUser = new DbUser("jim", enc.encode("567"), new String[] {"USER"});
    repo.save(dbUser);
  }
}

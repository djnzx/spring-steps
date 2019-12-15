package app.api.service;

import app.security.jpa.DbUser;
import app.security.jpa.DbUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

  private final DbUserRepo repo;
  private final PasswordEncoder enc;

  public AuthService(DbUserRepo repo, PasswordEncoder enc) {
    this.repo = repo;
    this.enc = enc;
  }

  public boolean register_new(String username, String password) {
    Optional<DbUser> found = repo.findByUsername(username);
    if (!found.isPresent()) {
      repo.save(new DbUser(username, enc.encode(password), "USER"));
    }
    return !found.isPresent();
  }
}

package app.api.service;

import app.security.jpa.DbUser;
import app.security.jpa.DbUserRepo;
import app.security.jwt.Const;
import app.security.jwt.JwtTokenService;
import app.security.ud.MyUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

  private final AuthenticationManager am;
  private final JwtTokenService tp;
  private final DbUserRepo repo;
  private final PasswordEncoder enc;

  public AuthService(AuthenticationManager am,
                     JwtTokenService tp,
                     DbUserRepo repo,
                     PasswordEncoder enc) {
    this.am = am;
    this.tp = tp;
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

  public Optional<String> login(String username, String password, boolean remember) {
    return Optional.of(am.authenticate(new UsernamePasswordAuthenticationToken(username, password)))
        .filter(Authentication::isAuthenticated)
        .map(a -> { SecurityContextHolder.getContext().setAuthentication(a); return  a; })
        .map(a -> (MyUserDetails) a.getPrincipal())
        .map(ud -> tp.generateToken(ud.getId(), remember))
        .map(t -> Const.AUTH_BEARER + t);
  }

}

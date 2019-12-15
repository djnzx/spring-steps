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

  private final AuthenticationManager authenticationManager;
  private final JwtTokenService tokenProvider;
  private final DbUserRepo repo;
  private final PasswordEncoder enc;

  public AuthService(AuthenticationManager authenticationManager,
                     JwtTokenService tokenProvider,
                     DbUserRepo repo,
                     PasswordEncoder enc) {
    this.authenticationManager = authenticationManager;
    this.tokenProvider = tokenProvider;
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
    // call Spring internal Authentication (((
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password)
    );
    // return empty if smth went wrong
    if (!authentication.isAuthenticated()) return Optional.empty();
    // set Authentication into current context
    SecurityContextHolder.getContext().setAuthentication(authentication);
    MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
    String token = tokenProvider.generateToken(user.getId(), remember);
    return Optional.of(String.format("%s%s", Const.AUTH_BEARER, token));
  }
}

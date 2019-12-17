package app.security.jwt;

import app.security.ud.UserDetailsServiceJPA;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenService tokenProvider;
  private final UserDetailsServiceJPA userDetailsService;

  public JwtAuthenticationFilter(
      JwtTokenService tokenProvider,
      UserDetailsServiceJPA userDetailsService) {
    this.tokenProvider = tokenProvider;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
//    logger.info("::::: JwtAuthenticationFilter:1");
    try {
      extractTokenFromRequest(request)                 // String
          .flatMap(tokenProvider::tokenToClaim)         // <Jws<Claims>>
          .map(tokenProvider::extractUserIdFromClaims) // long
          .map(userDetailsService::loadUserById)       // MyUserDetails
          .map(ud -> new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities())) // UsernamePasswordAuthenticationToken
          .ifPresent(auth -> {
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
//            logger.info("::::: JwtAuthenticationFilter:2");
          });
//      logger.info("::::: JwtAuthenticationFilter:3");
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      logger.error("Could not set user authentication in security context", ex);
    }
  }

  private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
    // getting header `Authorization`
    final String auth_header = request.getHeader(Const.AUTH_HEADER);

    // look for the token in the header
    if (StringUtils.hasText(auth_header)
        && auth_header.startsWith(Const.AUTH_BEARER)) {
      return Optional.of(auth_header.substring(Const.AUTH_BEARER.length())); // cut the "Bearer " substring
    }

    // look for the token in the param `?token=...`
    String par_token = request.getParameter(Const.AUTH_TOKEN);
    if (!StringUtils.isEmpty(par_token)) {
      return Optional.of(par_token.substring(Const.AUTH_BEARER.length()));
    }

    // nothing found...
    return Optional.empty();
  }
}

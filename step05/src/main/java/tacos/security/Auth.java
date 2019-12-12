package tacos.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface Auth {
  UserDetailsService content();
}

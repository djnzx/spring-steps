package tacos.security.auth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public interface Authentication {
  Collection<UserDetails> usersDetails();
}

package app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class UserDetailsHashMapEncoded {

  private final Map<String, String> storage = new HashMap<>();
  private final PasswordEncoder enc;

  public UserDetailsHashMapEncoded(PasswordEncoder enc) {
    this.enc = enc;
    storage.put("jim",  "123");
    storage.put("john", "234");
  }

  private UserDetails mapper(Map.Entry<String, String> entry) {
    return User
        .withUsername(entry.getKey())
        .password(entry.getValue())
        .passwordEncoder(enc::encode)
        .roles("USER") // all of the users have only one role: "USER"
        .build();
  }

//  @Bean
  public UserDetailsService udsHashMapEncoded() {
    Collection<UserDetails> data = storage.entrySet().stream()
        .map(this::mapper)
        .collect(Collectors.toSet());
    return new InMemoryUserDetailsManager(data);
  }
}

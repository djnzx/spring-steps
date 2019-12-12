package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;

@Configuration
public class MyDelegatedEncoder {

  private final static String ALGORITHM = "pbkdf2";

  @Bean
  public static PasswordEncoder build() {
    return new DelegatingPasswordEncoder(ALGORITHM,
        new HashMap<String, PasswordEncoder>(11) {{
          put("pbkdf2", new org.springframework.security.crypto.password.Pbkdf2PasswordEncoder());
          put("bcrypt", new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder());
          put("ldap", new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
          put("MD4", new org.springframework.security.crypto.password.Md4PasswordEncoder());
          put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
          put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
          put("scrypt", new org.springframework.security.crypto.scrypt.SCryptPasswordEncoder());
          put("SHA-1", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
          put("SHA-256", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));
          put("sha256", new org.springframework.security.crypto.password.StandardPasswordEncoder());
          put("argon2", new org.springframework.security.crypto.argon2.Argon2PasswordEncoder());
        }}
    );
  }
}

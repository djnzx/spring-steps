package app.entity.sec;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class XUserDetails implements UserDetails {

  private final int id;
  private final String username;
  private final String password;
  private final Collection<? extends GrantedAuthority> roles;

  public XUserDetails(int id, String username, String password, String[] roles) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.roles = Arrays.stream(roles)
        .map(s -> "ROLE_" + s)
        .map(s -> new GrantedAuthority() {
          @Override
          public String getAuthority() {
            return s;
          }

          @Override
          public String toString() {
            return String.format("GA:%s", s);
          }
        })
//        .map(s -> (GrantedAuthority) () -> s)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public String toString() {
    return String.format("XUserDetails[id=%d, username='%s', password='%s...', roles=%s]",
        id, username, password.substring(0, 10), getAuthorities());
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

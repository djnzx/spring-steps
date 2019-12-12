package app.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class DbUser {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String username;
  private String password;
  private String roles;

  private final String ROLES_DELIMITER = ":";

  public DbUser(String username, String password, String... roles) {
    this.username = username;
    this.password = password;
    setRoles(roles);
  }

  public String[] getRoles() {
    if (this.roles == null || this.roles.isEmpty()) return new String[]{};
    return this.roles.split(ROLES_DELIMITER);
  }

  public void setRoles(String[] roles) {
    this.roles = String.join(ROLES_DELIMITER, roles);
  }

}

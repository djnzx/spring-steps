package tacos.security.auth.sql;

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

  private String name;

  private String password;

  private String roles;

  public DbUser(String name, String password, String[] roles) {
    this.name = name;
    this.password = password;
    setRoles(roles);
  }

  public String[] getRoles() {
    return roles.split(":");
  }

  public void setRoles(String[] roles) {
    this.roles = String.join(":", roles);
  }
}

package tacos.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "usersabc")
public class DbUser {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String username;
  private String password;
  private String roles;

  public DbUser(String username, String password, String[] roles) {
    this.username = username;
    this.password = password;
    setRoles(roles);
  }

  public String[] getRoles() {
    if (roles == null || roles.equals("")) return new String[] { "USER" };
    return roles.split(":");
  }

  public void setRoles(String[] roles) {
    this.roles = String.join(":", roles);
  }
}

package app.entity.db;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class XUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String username;
  private String password;
  private String roles;

  /**
   * we do not need @Transient annotation for static fields
   * we need it to only static fields
   *
   * but I would put it resolve any kind of ambiguity
   */
  @Transient
  private final static String DELIMITER = ":";
  @Transient
  private final static String[] EMPTY = {};

  public XUser(String username, String password, String... roles) {
    this.username = username;
    this.password = password;
    setRoles(roles);
  }

  public String[] getRoles() {
    return roles == null || roles.isEmpty() ? EMPTY : roles.split(DELIMITER);
  }

  public void setRoles(String[] roles) {
    this.roles = String.join(DELIMITER, roles);
  }
}

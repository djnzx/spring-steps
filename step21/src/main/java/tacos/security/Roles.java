package tacos.security;

public class Roles {

  private static final String USER = "USER";
  private static final String ADMIN = "ADMIN";

  public static String[] a_user() {
    return new String[]{ USER };
  }

  public static String[] a_admin() {
    return new String[]{ ADMIN };
  }

  public static String[] a_all() {
    return new String[]{ USER, ADMIN };
  }

}

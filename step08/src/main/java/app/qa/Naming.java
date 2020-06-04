package app.qa;

public class Naming {
  
  int add(int a, int b) {
    throw new RuntimeException("should be implemented");
  }
  
  double add(double a, double b) {
    throw new RuntimeException("should be implemented");
  }
  
  void a() {
    int added_int = add(1, 1);
    double added_double = add(1.0, 1.0);
  }
}

package app.thymeleaf;

public class Product {
  public final String name;
  public final Double price;

  public Product(String name, Double price) {
    this.name = name;
    this.price = price;
  }
}

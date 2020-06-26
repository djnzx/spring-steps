package app.entity.api.stock;

import lombok.Value;

@Value
public class XStock {
  public String symbol;
  public Double price;
}

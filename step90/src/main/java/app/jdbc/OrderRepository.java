package app.jdbc;

import app.dto.Order;

public interface OrderRepository {
  Order save(Order order);
}

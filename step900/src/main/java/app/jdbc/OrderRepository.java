package app.jdbc;

import app.api.dto.Order;

public interface OrderRepository {
  Order save(Order order);
}

package app.controller;

import app.data.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Controller
@RequestMapping("/jdbc")
public class JdbcController {

  private JdbcTemplate jdbc;

  public Ingredient findOne(String id) {
    return jdbc.queryForObject(
        "select id, name, type from ingredient where id=?", this::mapRowToIngredient, id);
  }

  private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
    return new Ingredient(
        rs.getString("id"),
        rs.getString("name"),
        Ingredient.Type.valueOf(rs.getString("type")));
  }
}

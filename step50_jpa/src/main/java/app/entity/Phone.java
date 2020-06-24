package app.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Phone {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  String number;
}

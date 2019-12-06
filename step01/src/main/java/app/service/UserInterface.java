package app.service;

import app.entity.Person;

import java.util.List;

public interface UserInterface {
  List<Person> get();
  void add(String name);
}

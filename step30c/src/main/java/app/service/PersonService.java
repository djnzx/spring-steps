package app.service;

import app.entity.ext_api.student.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Log4j2
@Service
public class PersonService {

  private final RestTemplate rest;

  public PersonService(RestTemplate rest) {
    this.rest = rest;
  }

  public Student obtain_from_another_server() {
    // address + port
    String url = "http://localhost:8081/person";
    ResponseEntity<Student> response = rest.getForEntity(url, Student.class);
    HttpStatus code = response.getStatusCode();
    Student body = response.getBody();
    log.info(body);
    log.info(body.getId());
    log.info(body.getName());
    return body;
  }

  public Student obtain_from_another_server2() {
    // address + port
    String url = "http://localhost:8081/person";
    Student body = rest.getForObject(url, Student.class);
    log.info(body);
    log.info(body.getId());
    log.info(body.getName());
    return body;
  }

  public Optional<Student> obtain_from_another_server3() {
    // address + port
    String url = "http://localhost:8081/person";
    try {
      return Optional.ofNullable(rest.getForObject(url, Student.class));
    } catch (Exception x) {
      return Optional.empty();
    }
  }

}

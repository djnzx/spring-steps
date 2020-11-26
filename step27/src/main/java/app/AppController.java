package app;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
public class AppController {

  private final PersonRepo repo;

  @PostMapping("/create")
  public void create100() {
    IntStream.rangeClosed(1, 100)
        .forEach(x -> repo.save(Person.random()));
  }

  @GetMapping
  public List<Person> get(@RequestParam("page") int page, @RequestParam("size")int size) {
    PageRequest pr = PageRequest.of(page, size);
    return repo
        .findAll(pr)
        .getContent();
  }


}

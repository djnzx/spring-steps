package app.service;

import app.entity.Event;
import app.repo.EventRepo;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

  private final EventRepo eventRepo;

  public EventService(EventRepo eventRepo) {
    this.eventRepo = eventRepo;
  }

  /**
   * https://www.baeldung.com/spring-data-query-by-example
   * an exact match will be performed on all non-null properties
   * on String properties - the matching is case-sensitive
   */
  public void handle() {

    Example<Event> example = Example.of(new Event("Game1", 155));
    List<Event> all1 = eventRepo.findAll(example);
    List<Event> all2 = eventRepo.findAll(example, Sort.by(Sort.Direction.ASC));
    Page<Event> page = eventRepo.findAll(example, PageRequest.of(3, 20, Sort.by("delta")));
    page.forEach(e -> System.out.println(e));
  }
}

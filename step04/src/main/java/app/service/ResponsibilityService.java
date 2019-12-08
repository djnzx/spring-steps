package app.service;

import app.entity.Responsibility;
import app.repository.ResponsibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ResponsibilityService {

  private final ResponsibilityRepository responsibilityRepository;

  @Autowired
  public ResponsibilityService(ResponsibilityRepository responsibilityRepository) {
    this.responsibilityRepository = responsibilityRepository;
  }

  public Collection<Responsibility> create_initial() {
    List<Responsibility> rs = Stream.of(
        "Manager", "Director", "Developer", "Smart"
    )
        .map(s -> new Responsibility(0, s))
        .collect(Collectors.toList());
    responsibilityRepository.saveAll(rs);
    return rs;
  }

  public Iterable<Responsibility> get_all() {
    return responsibilityRepository.findAll();
  }

  public Optional<Responsibility> get_one(long pk) {
    return responsibilityRepository.findById(pk);
  }

  public Responsibility create_one(Responsibility r) {
    responsibilityRepository.save(r);
    return r;
  }

}

package app.controller;

import app.entity.Artifact;
import app.entity.Tag;
import app.service.MavenParserService3;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/maven")
public class MavenController {

  private final MavenParserService3 mavenParserService;

  public MavenController(MavenParserService3 mavenParserService) {
    this.mavenParserService = mavenParserService;
  }

  /**
   * http://localhost:8080/maven/artifacts
   */
  @GetMapping("artifacts")
  public Collection<Artifact> handle_news() {
    return mavenParserService.artifacts();
  }

  /**
   * http://localhost:8080/maven/tags
   */
  @GetMapping("tags")
  public Collection<Tag> handle_tags() {
    return mavenParserService.tags();
  }

}

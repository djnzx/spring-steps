package app.controller;

import app.entity.Artifact;
import app.entity.Tag;
import app.service.MavenParserService;
import app.service.MavenParserService9;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/maven")
public class MavenController {

  private final MavenParserService9 mavenParser;

  public MavenController(MavenParserService9 mavenParser) {
    this.mavenParser = mavenParser;
  }

  /**
   * http://localhost:8080/maven/artifacts
   */
  @GetMapping("artifacts")
  public Collection<Artifact> handle_artifacts() {
    return mavenParser.artifacts();
  }

  /**
   * http://localhost:8080/maven/tags
   */
  @GetMapping("tags")
  public Collection<Tag> handle_tags() {
    return mavenParser.tags();
  }

}

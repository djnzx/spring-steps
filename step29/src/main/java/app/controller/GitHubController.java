package app.controller;

import app.entity.Artifact;
import app.entity.Tag;
import app.service.GithubParserService;
import app.service.MavenParserService9;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/github")
public class GitHubController {

  private final GithubParserService githubParserService;

  public GitHubController(GithubParserService githubParserService) {
    this.githubParserService = githubParserService;
  }

  /**
   * http://localhost:8080/github/github
   */
  @GetMapping("github")
  public Collection<Artifact> handle_artifacts() {
    return githubParserService.news();
  }

}

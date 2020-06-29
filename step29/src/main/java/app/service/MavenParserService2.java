package app.service;

import app.entity.Artifact;
import app.entity.Tag;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Service
public class MavenParserService2 {

  private final RestTemplate rest;
  private final String siteAddress = "https://mvnrepository.com";

  private HttpHeaders pretend_browser() {
    return new HttpHeaders() {{
      add("user-agent",
          "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    }};
  }

  private String rootPageFromWeb(String url) {
    HttpEntity<Object> rqEntity = new HttpEntity<>(pretend_browser());
    return rest.exchange(url, HttpMethod.GET, rqEntity, String.class).getBody();
  }

  @SneakyThrows
  private String rootPageFromFile(String fname) {
    String file = this.getClass().getClassLoader().getResource(fname).getFile();
    return String.join("\n", Files.readAllLines(Paths.get(file)));
  }

  public MavenParserService2(RestTemplate rest) {
    this.rest = rest;
  }

  public Collection<Artifact> artifacts() {
    String mavenRootPage =
        rootPageFromWeb(siteAddress);
//        rootPageFromFile("response.html");
    Document doc = Jsoup.parse(mavenRootPage);
    Elements posts = doc.select("body #page #maincontent .posts .im");
    return posts.stream()
        .map((Element e) -> {
          Elements els = e.select(".im > a");
          String href = els.attr("href");
          String url = els.select("picture img").attr("src");
          return new Artifact(href, siteAddress + url);
        })
        .collect(Collectors.toList());
  }

  private String betweenQuotes(String s) {
    return s.substring(s.indexOf("'") + 1, s.lastIndexOf("'"));
  }

  private String betweenBrackets(String s) {
    return s.substring(s.indexOf("[") + 1, s.indexOf("]"));
  }

  private <A> List<A> process(
      String[] scriptLines,
      String prefix,
      Function<String, A> mapper) {
    return Arrays.stream(scriptLines)
        .filter(l -> l.startsWith(prefix))
        .findFirst()
        .map(this::betweenBrackets)
        .map(s -> s.split(","))
        .map(Arrays::stream)
        .orElseThrow(RuntimeException::new)
        .map(mapper)
        .collect(Collectors.toList());
  }

  public Collection<Tag> tags() {
    String mavenRootPage = rootPageFromWeb(siteAddress);
    Document doc = Jsoup.parse(mavenRootPage);
    String[] scriptLines = doc.select("body #right > div > script").html()
        .split("\n");

    List<String> tags = process(
        scriptLines,
        "var tags",
        this::betweenQuotes);
    List<Integer> scores = process(
        scriptLines,
        "var sizes",
        Integer::parseInt);

    return IntStream.range(0, Math.min(tags.size(), scores.size())).mapToObj(i ->
        new Tag(tags.get(i), scores.get(i)))
        .sorted(Comparator.comparingInt(t -> -t.score))
        .collect(Collectors.toList());
  }
}

package app.service;

import app.entity.Artifact;
import app.entity.Tag;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Service
public class MavenParserService {

  private final RestTemplate rest;
  private final String addrRoot = "https://mvnrepository.com";

  public MavenParserService(RestTemplate rest) {
    this.rest = rest;
  }

  private String grabPage(String url) {
    return rest.getForObject(url, String.class);
  }

  private Document rootDocument() {
    String root = grabPage(addrRoot);
    return Jsoup.parse(root);
  }

  /**
   * https://jsoup.org/apidocs/org/jsoup/select/Selector.html
   * "div"   <div>
   * ".abc"  <class="abc">
   * "#cde"  <p id="cde">
   */
  public Collection<Artifact> artifacts() {
    Document doc = rootDocument();
    Elements posts = doc.select("body #maincontent .posts .im");

    return posts.stream()
        .map(e -> new Artifact(e.select(".im > a").attr("href")))
        .collect(Collectors.toList());
  }

  private String betweenQuotes(String s) {
    return s.substring(s.indexOf('\'')+1, s.lastIndexOf('\''));
  }

  private String betweenBrackets(String s) {
    return s.substring(s.indexOf('[')+1, s.indexOf(']'));
  }

  private <B> List<B> process(String[] script, String key, Function<String, B> mapper) {
    return Arrays.stream(script)
        .filter(l -> l.startsWith(key))
        .findFirst()
        .map(this::betweenBrackets)
        .map(s -> s.split(","))
        .map(Arrays::stream)
        .orElseThrow(RuntimeException::new)
        .map(mapper)
        .collect(Collectors.toList());
  }

  public Collection<Tag> tags() {
    Document doc = rootDocument();
    String script = doc.select("body #right > div > script").html();
    String[] scriptLines = script.split("\n");

    List<String> tags = process(scriptLines, "var tags", this::betweenQuotes);
    List<Integer> scores = process(scriptLines, "var sizes", Integer::parseInt);

    return IntStream.range(0, Math.min(tags.size(), scores.size())).mapToObj(i ->
        new Tag(scores.get(i), tags.get(i)))
        .sorted(Comparator.comparingInt(t -> -t.getScore()))
        .collect(Collectors.toList());
  }
}

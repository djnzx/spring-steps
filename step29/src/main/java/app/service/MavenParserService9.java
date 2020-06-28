package app.service;

import app.entity.Artifact;
import app.entity.Tag;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class MavenParserService9 {

  private final RestTemplate rest;
  private final String addrRoot = "https://mvnrepository.com";

  public MavenParserService9(RestTemplate rest) {
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

  private <B> Spliterator<B> process(String[] script, String key, Function<String, B> mapper) {
    return Arrays.stream(script)
        .filter(l -> l.startsWith(key))
        .findFirst()
        .map(this::betweenBrackets)
        .map(s -> s.split(","))
        .map(Arrays::stream)
        .orElseThrow(RuntimeException::new)
        .map(mapper)
        .spliterator();
  }

  public Collection<Tag> tags() {
    Document doc = rootDocument();
    String script = doc.select("body #right > div > script").html();
    String[] scriptLines = script.split("\n");
    Spliterator<String> tags = process(scriptLines, "var tags", this::betweenQuotes);
    Spliterator<Integer> scores = process(scriptLines, "var sizes", Integer::parseInt);
    long size = Math.min(tags.getExactSizeIfKnown(), scores.getExactSizeIfKnown());

    Iterator<Tag> it = new Iterator<Tag>() {
      final Iterator<String> it_tags = Spliterators.iterator(tags);
      final Iterator<Integer> it_scores = Spliterators.iterator(scores);

      @Override
      public boolean hasNext() {
        return it_tags.hasNext() && it_scores.hasNext();
      }

      @Override
      public Tag next() {
        return new Tag(it_scores.next(), it_tags.next());
      }
    };

    return StreamSupport.stream(
        Spliterators.spliterator(
            it, size, tags.characteristics()
        ),
        false)
        .sorted(Comparator.comparingInt(t -> -t.getScore()))
        .collect(Collectors.toList());
  }
}

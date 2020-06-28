package app.service;

import app.entity.Artifact;
import app.entity.Tag;
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

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Service
public class GithubParserService {

  private final RestTemplate rest;
  private final String addrRoot = "https://github.com";

  public GithubParserService(RestTemplate rest) {
    this.rest = rest;
  }

  private String kv(String k, String v) {
    return String.format("%s=%s", k, v);
  }

  private HttpHeaders auth() {
    String[][] cookies = {
        {"username", "v1"},
        {"k2", "v2"},
    };

    return new HttpHeaders() {{
      Arrays.stream(cookies)
          .forEach(c -> add("Cookie", kv(c[0], c[1])));
    }};
  }

  private String grabPage(String url) {
    HttpEntity<Object> rqEntity = new HttpEntity<>(auth());
    return rest.exchange(url, HttpMethod.GET, rqEntity, String.class).getBody();
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
  public Collection<Artifact> news() {
    Document doc = rootDocument();
    Element news = doc.select("#dashboard .news > div").get(1);
    log.info(news);

    System.exit(0);
    throw new RuntimeException("should be implemented");
  }

}

package app.service;

import app.entity.api.quote.XQuote;
import app.entity.ext_api.qoute.Quote;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuoteService {

  private final RestTemplate rest;
  private static final String url = "https://gturnquist-quoters.cfapps.io/api/random";

  public QuoteService(RestTemplate rest) {
    this.rest = rest;
  }

  private String oneQuoteText() {
    return rest.getForObject(url, String.class);
  }

  private Quote oneQuote() {
    return rest.getForObject(url, Quote.class);
  }

  public List<String> obtain_next_quote_string() {
    return Stream.generate(this::oneQuoteText)
        .limit(5)
        .collect(Collectors.toList());
  }

  public List<Quote> obtain_next_quote() {
    return Stream.generate(this::oneQuote)
        .limit(5)
        .collect(Collectors.toList());
  }

  public List<XQuote> obtain_next_quotex() {
    return Stream.generate(this::oneQuote)
        .limit(5)
        .map(Quote::getValue)
        .map(q -> new XQuote(q.getId(), q.getQuote()))
        .collect(Collectors.toList());
  }
}

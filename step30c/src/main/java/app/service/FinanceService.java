package app.service;

import app.entity.api.stock.XStock;
import app.entity.ext_api.yahoo_finace.YResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceService {

  private final RestTemplate rest;
  private final String url = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-summary?region=US&lang=en";

  public FinanceService(RestTemplate rest) {
    this.rest = rest;
  }

  private HttpHeaders auth() {
    return new HttpHeaders() {{
      add("x-rapidapi-host", new String(System.getenv("x-rapidapi-host")));
      add("x-rapidapi-key", new String(System.getenv("x-rapidapi-key")));
    }};
  }

  private HttpEntity<Object> rqHttpEntity() {
    return new HttpEntity<>(auth());
  }

  public YResponse get_stocks() {
    ResponseEntity<YResponse> resp = rest.exchange(url, HttpMethod.GET, rqHttpEntity(), YResponse.class);
    return resp.getBody();
  }

  public List<XStock> get_stocksx() {
    ResponseEntity<YResponse> resp = rest.exchange(url, HttpMethod.GET, rqHttpEntity(), YResponse.class);
    return resp.getBody()
        .getMarketSummaryResponse()
        .getResult().stream()
        .map(i -> new XStock(
            i.getSymbol(),
            i.getRegularMarketPrice().getRaw()
        ))
        .sorted(Comparator.comparingDouble(s -> -s.price))
        .collect(Collectors.toList());
  }
}

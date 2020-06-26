package app.controller;

import app.entity.ext_api.student.Student;
import app.entity.api.quote.XQuote;
import app.entity.api.stock.XStock;
import app.entity.ext_api.qoute.Quote;
import app.entity.ext_api.yahoo_finace.YResponse;
import app.service.FinanceService;
import app.service.PersonService;
import app.service.QuoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {

  private final PersonService personService;
  private final QuoteService quoteService;
  private final FinanceService financeService;

  public MainController(PersonService personService, QuoteService quoteService, FinanceService financeService) {
    this.personService = personService;
    this.quoteService = quoteService;
    this.financeService = financeService;
  }

  /**
   * http://localhost:8080/main/person
   */
  @GetMapping("person")
  public Student handle1() {
    return personService.obtain_from_another_server();
  }

  /**
   * http://localhost:8080/main/quotes
   */
  @GetMapping("quotes")
  public List<String> handle2() {
    return quoteService.obtain_next_quote_string();
  }

  /**
   * http://localhost:8080/main/quote
   */
  @GetMapping("quote")
  public List<Quote> handle3() {
    return quoteService.obtain_next_quote();
  }

  /**
   * http://localhost:8080/main/quotex
   */
  @GetMapping("quotex")
  public List<XQuote> handle4() {
    return quoteService.obtain_next_quotex();
  }

  /**
   * http://localhost:8080/main/finance
   */
  @GetMapping("finance")
  public YResponse handle5() {
    return financeService.get_stocks();
  }

  /**
   * http://localhost:8080/main/financex
   */
  @GetMapping("financex")
  public List<XStock> handle6() {
    return financeService.get_stocksx();
  }
}

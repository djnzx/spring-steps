package app.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Formatter2 implements Formatter {

  @Bean
  public Formatter formatter_V2() {
    return new Formatter2();
  }

  @Override
  public String format(String origin) {
    return String.format("2:<<<%s>>>", origin);
  }

}

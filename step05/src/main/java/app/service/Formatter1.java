package app.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class Formatter1 implements Formatter {

  @Override
  public String format(String origin) {
    return String.format("1:>>>%s<<<", origin);
  }

}

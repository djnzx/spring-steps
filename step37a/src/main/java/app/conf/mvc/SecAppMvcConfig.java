package app.conf.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class SecAppMvcConfig implements WebMvcConfigurer {

  private final static String[][] mappings = {
      {"/",      "menu"},
      {"/guest", "guest"},
      {"/home",  "home"},
      {"/admin", "admin"},
      {"/me",    "me"},
      {"/news",  "news"}
  };

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    Arrays.stream(mappings).forEach(m ->
        registry.addViewController(m[0])
            .setViewName(m[1])
        );
  }
}

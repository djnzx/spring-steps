package app.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.IntStream;

@Configuration
@EnableWebMvc
public class StaticResourcesConfig implements WebMvcConfigurer {
  public static final String[] MAP = { "/css/**", "/js/**", "/img/**", "/html/**" };
  private final String[] LOC = {
      "classpath:/static/css/",
      "classpath:/static/js/",
      "classpath:/static/img/",
      "classpath:/static/html/"
  };

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry reg) {
    if (MAP.length != LOC.length) throw new IllegalArgumentException(
        "StaticResourcesConfig: arrays MAP and LOC must have the same size");
    IntStream.range(0, MAP.length)
        .forEach(idx -> reg.addResourceHandler(MAP[idx])
            .addResourceLocations(LOC[idx]));
  }
}

package app.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * But any configuration class can implement WebMvcConfigurer
 * and override the addViewController method
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    // having that - you don't need the separate controller for that stuff
    registry.addViewController("/").setViewName("home");
  }

}

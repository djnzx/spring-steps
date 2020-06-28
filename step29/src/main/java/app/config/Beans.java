package app.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Beans {

  @Bean
  public RestTemplate buildRestTemplate(RestTemplateBuilder restTemplateBuilder) {
    System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
    return restTemplateBuilder.build();
  }

}

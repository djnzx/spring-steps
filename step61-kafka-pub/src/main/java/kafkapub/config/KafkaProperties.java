package kafkapub.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * just a container for
 * part of `application.yml` file
 */
@Data
@Configuration
@ConfigurationProperties("kafka")
public class KafkaProperties {
  private String topic;
  private String bootstrapServers;
  private Map<String, String> producerProperties = new HashMap<>();
}

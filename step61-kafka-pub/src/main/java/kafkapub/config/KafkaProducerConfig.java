package kafkapub.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

  public ProducerFactory<String, String> producerFactory(KafkaProperties kafkaProperties) {
    // hashMap from configuration
    Map<String, Object> configProps = new HashMap<>(kafkaProperties.getProducerProperties());
    // extra parameters
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());

    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(KafkaProperties kafkaProperties) {
    return new KafkaTemplate<>(producerFactory(kafkaProperties));
  }
}

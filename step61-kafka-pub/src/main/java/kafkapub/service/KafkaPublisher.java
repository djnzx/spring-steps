package kafkapub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafkapub.config.KafkaProperties;
import kafkapub.dto.Person;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@RequiredArgsConstructor
public class KafkaPublisher {

  private final KafkaTemplate<String, String> template;
  private final KafkaProperties props;
  private final ObjectMapper om;

  @SneakyThrows
  public ListenableFuture<SendResult<String, String>> sendPerson(Person p) {
    String key = om.writeValueAsString(p.getId());
    String value = om.writeValueAsString(p);
    return template.send(props.getTopic(), key, value);
  }

}

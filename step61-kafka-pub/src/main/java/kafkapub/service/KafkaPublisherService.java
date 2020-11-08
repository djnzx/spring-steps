package kafkapub.service;

import kafkapub.dto.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Log4j2
@Service
@EnableScheduling
@RequiredArgsConstructor
public class KafkaPublisherService {

  private final KafkaPublisher pub;

  @Scheduled(fixedDelay = 5000)
  public void sendOnce() {
    Person p = new Person(new Random().nextInt(), "Jim");
    log.info("Sending person {}", p);
    pub.sendPerson(p);
  }
}

package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringAppClient {
  public static void main(String[] args) {
    SpringApplication.run(SpringAppClient.class);
  }
}

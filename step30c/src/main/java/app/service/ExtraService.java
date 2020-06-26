package app.service;

import app.entity.ext_api.student.Student;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Objects;

@Service
public class ExtraService {

  private final RestTemplate rest;

  public ExtraService(RestTemplate rest) {
    this.rest = rest;
  }

  /**
   * file download to byte[]
   */
  public byte[] downloadFile(String url) {
    return rest.getForObject(url, byte[].class);
  }

  /**
   * file download to InputStream
   * https://dictionary.cambridge.org/media/english/uk_pron/u/ukp/ukpen/ukpente007.mp3
   */
  public InputStream downloadFileStream(String url) {
    return new ByteArrayInputStream(
        Objects.requireNonNull(rest.getForObject(url, byte[].class)));
  }

  /**
   * file(s) upload
   */
  public void uploadFiles(String url) {
    // headers
    HttpHeaders headers = new HttpHeaders() {{
      setContentType(MediaType.MULTIPART_FORM_DATA);
    }};
    // available to download via MultipartFile (lesson 54)
    LinkedMultiValueMap<String, Object> contents = new LinkedMultiValueMap<String, Object>() {{
      add("file1", new FileSystemResource("file1.avi"));
      add("file2", new FileSystemResource("file2.avi"));
      add("file3", new FileSystemResource("file3.avi"));
    }};
    // entity
    HttpEntity<LinkedMultiValueMap<String, Object>> rqHttpEntity = new HttpEntity<>(contents, headers);

    /** you can polish String to smth more explicit later */
    ResponseEntity<String> response = rest.exchange(url, HttpMethod.POST, rqHttpEntity, String.class);
  }

  /**
   * send POST with Entity
   */
  public void sendEntity(String url) {
    // headers
    HttpHeaders headers = new HttpHeaders() {{
      setContentType(MediaType.APPLICATION_JSON);
    }};
    // body
    Student jackson = new Student(55, "Jackson");
    // entity
    HttpEntity<Student> rqHttpEntity = new HttpEntity<>(jackson, headers);
    // run
    ResponseEntity<String> response = rest.exchange(url, HttpMethod.POST, rqHttpEntity, String.class);
  }

  /**
   * sending form
   */
  public void sendForm(String url) {
    // headers
    HttpHeaders headers = new HttpHeaders() {{
      setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    }};
    // contents
    LinkedMultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>() {{
      add("login", "jim");
      add("possword", "123");
    }};
    // entity
    HttpEntity<LinkedMultiValueMap<String, Object>> rqHttpEntity = new HttpEntity<>(form, headers);
    // send
    ResponseEntity<String> resp1 = rest.exchange     (url, HttpMethod.POST, rqHttpEntity, String.class);
    ResponseEntity<String> resp2 = rest.postForEntity(url,                  rqHttpEntity, String.class);
  }

}

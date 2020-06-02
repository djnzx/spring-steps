package app.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * https://www.baeldung.com/spring-classpath-file-access
 */
@Controller
@RequestMapping("/files")
public class FileDownloadController {

  private static String fmt(String format, Object... args) {
    return String.format(format, args);
  }

  /**
   * http://localhost:8080/files/file_x.txt
   */
  @GetMapping("{filename:.+}")
  public ResponseEntity<Resource> get(@PathVariable String filename) {
    Resource file = new ClassPathResource(filename);
    return ResponseEntity.ok()
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            fmt( "attachment; filename=\"%s\"", file.getFilename()))
        .body(file);
  }

}

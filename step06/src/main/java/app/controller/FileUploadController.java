package app.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@Controller
@RequestMapping("/file")
public class FileUploadController {

  private static String fmt(String format, Object... args) {
    return String.format(format, args);
  }

  /**
   * http://localhost:8080/file
   */
  @GetMapping
  public String handle_get() {
    log.info("GET  -> /file");
    return "form_file";
  }

  @PostMapping
  @ResponseBody
  public String handle_post(@RequestParam("filea") MultipartFile file) throws IOException {
    String contents = new String(file.getBytes());
    log.info(fmt("POST -> /file contents=%s", contents));
    return fmt("GOT: %s", contents);
  }

}

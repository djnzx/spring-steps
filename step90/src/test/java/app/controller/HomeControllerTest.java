package app.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)                         // 1. test for HomeController.class
public class HomeControllerTest {

  @Autowired
  private MockMvc mockMvc;                                // 2. MockMvc injection

  @Test
  public void testHomePage() throws Exception {
    mockMvc.perform(get("/"))                  // 3. GET performing
        .andExpect(status().isOk())                       // 4. Expects HTTP 200 OK
        .andExpect(view().name("home"))  // 5. The view should have a logical name of home
        .andExpect(content().string(
            containsString("Welcome to...")));   // 6. Expects Welcome to...
  }

}

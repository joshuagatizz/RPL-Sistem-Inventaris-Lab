package com.example.sisteminventarislab;

import com.example.sisteminventarislab.entity.AccessToken;
import com.example.sisteminventarislab.repository.AccessTokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@WebAppConfiguration
@TestPropertySource({"/application-test.properties"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class SistemInventarisLabApplicationTests {

  protected final static String TEST_ACCESS_TOKEN = "TEST_ACCESS_TOKEN";

  @Autowired
  protected AccessTokenRepository accessTokenRepository;

  @Autowired
  protected WebApplicationContext webApplicationContext;

  protected static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

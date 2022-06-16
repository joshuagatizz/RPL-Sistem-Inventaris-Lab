package com.example.sisteminventarislab;

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
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {})
public abstract class SistemInventarisLabApplicationTests {
  @Autowired
  protected WebApplicationContext webApplicationContext;

}

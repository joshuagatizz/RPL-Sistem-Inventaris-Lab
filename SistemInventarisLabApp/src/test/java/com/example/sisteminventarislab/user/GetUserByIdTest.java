package com.example.sisteminventarislab.user;

import com.example.sisteminventarislab.SistemInventarisLabApplicationTests;
import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.repository.UserRepository;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GetUserByIdTest extends SistemInventarisLabApplicationTests {

  @Autowired
  private UserRepository repository;

  private static final String url = "/api/user";
  private MockMvc mockMvc;
  private User savedData;

  @BeforeAll
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.savedData = User.builder().nama("bambang2").nim("1301190002").tipeUser("tipe2").password("pass2").email("testemail2@gmail.com").build();

    repository.save(User.builder().nama("bambang1").nim("1301190001").tipeUser("tipe1").password("pass1").email("testemail1@gmail.com").build());
    repository.save(savedData);
    repository.save(User.builder().nama("bambang3").nim("1301190003").tipeUser("tipe3").password("pass3").email("testemail3@gmail.com").build());
  }

  @AfterAll
  public void tearDown() {
    repository.deleteAll();
  }

  @Test
  public void getUserById_success() throws Exception {
    mockMvc.perform(
        get(url + "/" + savedData.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.data.id", equalTo(savedData.getId())))
        .andExpect(jsonPath("$.data.nim", equalTo(savedData.getNim())))
        .andExpect(jsonPath("$.data.tipeUser", equalTo(savedData.getTipeUser())))
        .andExpect(jsonPath("$.data.password", equalTo(savedData.getPassword())))
        .andExpect(jsonPath("$.data.email", equalTo(savedData.getEmail())));
  }

  @Test
  public void getUserById_failed_userNotFound() throws Exception {
    mockMvc.perform(
        get(url + "/" + "randomid"))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)))
        .andExpect(jsonPath("$.errors[0]", equalTo(ErrorCode.USER_NOT_FOUND.getMessage())));
  }
}

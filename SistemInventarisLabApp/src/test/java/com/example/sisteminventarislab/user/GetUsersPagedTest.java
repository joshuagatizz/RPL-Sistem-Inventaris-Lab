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


public class GetUsersPagedTest extends SistemInventarisLabApplicationTests {

  @Autowired
  private UserRepository repository;

  private static final String url = "/api/user";
  private MockMvc mockMvc;

  @BeforeAll
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    repository.save(User.builder().nama("bambang1").nim("1301190001").tipeUser("tipe1").password("pass1").email("testemail1@gmail.com").build());
    repository.save(User.builder().nama("bambang2").nim("1301190002").tipeUser("tipe2").password("pass2").email("testemail2@gmail.com").build());
    repository.save(User.builder().nama("bambang3").nim("1301190003").tipeUser("tipe3").password("pass3").email("testemail3@gmail.com").build());
    repository.save(User.builder().nama("bambang4").nim("1301190004").tipeUser("tipe4").password("pass4").email("testemail4@gmail.com").build());
    repository.save(User.builder().nama("bambang5").nim("1301190005").tipeUser("tipe5").password("pass5").email("testemail5@gmail.com").build());
    repository.save(User.builder().nama("bambang6").nim("1301190006").tipeUser("tipe6").password("pass6").email("testemail6@gmail.com").build());
    repository.save(User.builder().nama("bambang7").nim("1301190007").tipeUser("tipe7").password("pass7").email("testemail7@gmail.com").build());
    repository.save(User.builder().nama("bambang8").nim("1301190008").tipeUser("tipe8").password("pass8").email("testemail8@gmail.com").build());
  }

  @AfterAll
  void tearDown() {
    repository.deleteAll();
  }

  @Test
  public void getUsersPaged_success() throws Exception {
    mockMvc.perform(
        get(url).param("page", String.valueOf(1)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.data", IsCollectionWithSize.hasSize(4)));
  }

  @Test
  public void getUsersPaged_failed_invalidInput() throws Exception {
    mockMvc.perform(
        get(url).param("page", String.valueOf(-1)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)))
        .andExpect(jsonPath("$.errors[0]", equalTo(ErrorCode.INVALID_PAGE_INPUT.getMessage())));;
  }

  @Test
  public void getUsersPaged_failed_inputExceedsPageLimit() throws Exception {
    mockMvc.perform(
        get(url).param("page", String.valueOf(4)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)))
        .andExpect(jsonPath("$.errors[0]", equalTo(ErrorCode.PAGE_LIMIT_EXCEEDED.getMessage())));
  }
}

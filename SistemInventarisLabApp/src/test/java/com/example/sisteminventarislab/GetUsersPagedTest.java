package com.example.sisteminventarislab;

import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.entity.helper.ResponseHelper;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.repository.UserRepository;
import com.example.sisteminventarislab.web.controller.UserController;
import org.assertj.core.api.Assert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GetUsersPagedTest extends SistemInventarisLabApplicationTests {

  @Autowired
  private UserRepository repository;

  private static final String url = "/api/user";
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    repository.save(User.builder().nama("bambang1").tipeUser("tipe1").password("pass1").email("testemail1@gmail.com").build());
    repository.save(User.builder().nama("bambang2").tipeUser("tipe2").password("pass2").email("testemail2@gmail.com").build());
    repository.save(User.builder().nama("bambang3").tipeUser("tipe3").password("pass3").email("testemail3@gmail.com").build());
    repository.save(User.builder().nama("bambang4").tipeUser("tipe4").password("pass4").email("testemail4@gmail.com").build());
    repository.save(User.builder().nama("bambang5").tipeUser("tipe5").password("pass5").email("testemail5@gmail.com").build());
    repository.save(User.builder().nama("bambang6").tipeUser("tipe6").password("pass6").email("testemail6@gmail.com").build());
    repository.save(User.builder().nama("bambang7").tipeUser("tipe7").password("pass7").email("testemail7@gmail.com").build());
    repository.save(User.builder().nama("bambang8").tipeUser("tipe8").password("pass8").email("testemail8@gmail.com").build());
  }

  @AfterEach
  void reset() {
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

//  @Test
//  public void getUsersPaged_failed_negativeInput() throws Exception {
//    mockMvc.perform(
//            get(url).param("page", String.valueOf(-1)))
//        .andExpect(status().isBadRequest())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
//  }
//
//  @Test
//  public void getUsersPaged_failed_emptyInput() throws Exception {
//    mockMvc.perform(
//            get(url).param("page", " "))
//        .andExpect(status().isBadRequest())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
//  }

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

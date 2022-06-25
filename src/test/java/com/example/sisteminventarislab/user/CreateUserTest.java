package com.example.sisteminventarislab.user;

import com.example.sisteminventarislab.SistemInventarisLabApplicationTests;
import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.repository.UserRepository;
import com.example.sisteminventarislab.web.model.Request.CreateUserWebRequest;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CreateUserTest extends SistemInventarisLabApplicationTests {

  @Autowired
  private UserRepository repository;

  private static final String url = "/api/user";
  private MockMvc mockMvc;
  private CreateUserWebRequest request;

  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.request = CreateUserWebRequest.builder()
        .nama("bambang1")
        .nim("1301190001")
        .tipeUser("tipe1")
        .password("pass1")
        .email("testemail1@gmail.com")
        .build();
  }

  @AfterEach
  public void tearDown() {
    repository.deleteAll();
  }

  @Test
  public void createUser_success() throws Exception {
    mockMvc.perform(
        post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(request)))
        .andExpect(status().isOk());

    User result = repository.findAll().get(0);
    Assertions.assertEquals(request.getNama(), result.getNama());
    Assertions.assertEquals(request.getNim(), result.getNim());
    Assertions.assertEquals(request.getEmail(), result.getEmail());
    Assertions.assertEquals(request.getPassword(), result.getPassword());
    Assertions.assertEquals(request.getTipeUser(), result.getTipeUser());
  }

  @Test
  public void createUser_failed_nameIsEmpty() throws Exception {
    request.setNama("");

    mockMvc.perform(
        post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Nama tidak boleh kosong")));
  }

  @Test
  public void createUser_failed_duplicateNim() throws Exception {
    mockMvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(request)));

    mockMvc.perform(
        post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)))
        .andExpect(jsonPath("$.errors[0]", equalTo(ErrorCode.USER_NIM_ALREADY_EXISTS.getMessage())));;
  }
}

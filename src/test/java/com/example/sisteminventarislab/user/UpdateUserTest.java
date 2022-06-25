package com.example.sisteminventarislab.user;

import com.example.sisteminventarislab.SistemInventarisLabApplicationTests;
import com.example.sisteminventarislab.entity.AccessToken;
import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.repository.UserRepository;
import com.example.sisteminventarislab.web.model.Request.UpdateUserWebRequest;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UpdateUserTest extends SistemInventarisLabApplicationTests {
  @Autowired
  private UserRepository repository;

  private static final String url = "/api/user";
  private MockMvc mockMvc;
  private User savedData;
  private UpdateUserWebRequest updateData;

  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.savedData = User.builder().nama("bambang1").nim("1301190001").tipeUser("tipe1").imgUrl("url1").password("pass1").email("testemail1@gmail.com").build();
    this.updateData = UpdateUserWebRequest.builder().nama("bambang2").tipeUser("tipe2").imgUrl("url2").password("pass2").email("testemail2@gmail.com").build();
    repository.save(savedData);
    AccessToken token = AccessToken.builder().token(TEST_ACCESS_TOKEN).access(1).build();
    accessTokenRepository.save(token);
  }

  @AfterEach
  public void tearDown() {
    repository.deleteAll();
    accessTokenRepository.deleteAll();
  }

  @Test
  public void updateUser_success() throws Exception {
    mockMvc.perform(
        put(url + "/" + savedData.getId())
            .param("token", TEST_ACCESS_TOKEN)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(updateData)))
        .andExpect(status().isOk());

    User result = repository.findAll().get(0);
    Assertions.assertEquals(updateData.getNama(), result.getNama());
    Assertions.assertEquals(updateData.getEmail(), result.getEmail());
    Assertions.assertEquals(updateData.getPassword(), result.getPassword());
    Assertions.assertEquals(updateData.getTipeUser(), result.getTipeUser());
    Assertions.assertEquals(updateData.getImgUrl(), result.getImgUrl());
  }

  @Test
  public void updateUser_failed_dataNotFound() throws Exception {
    mockMvc.perform(
        put(url + "/" + "randomid")
            .param("token", TEST_ACCESS_TOKEN)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(updateData)))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)));
  }

  @Test
  public void updateUser_failed_nameIsEmpty() throws Exception {
    updateData.setNama("");

    mockMvc.perform(
        put(url + "/" + "randomid")
            .param("token", TEST_ACCESS_TOKEN)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(updateData)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)));
  }
}

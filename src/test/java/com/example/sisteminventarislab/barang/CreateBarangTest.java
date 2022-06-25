package com.example.sisteminventarislab.barang;

import com.example.sisteminventarislab.SistemInventarisLabApplicationTests;
import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.repository.BarangRepository;
import com.example.sisteminventarislab.web.model.Request.CreateBarangWebRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CreateBarangTest extends SistemInventarisLabApplicationTests {
  @Autowired
  private BarangRepository repository;

  private static final String url = "/api/barang";
  private MockMvc mockMvc;
  private CreateBarangWebRequest request;

  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.request = CreateBarangWebRequest.builder()
        .nama("barang")
        .deskripsi("deskripsi")
        .urlFoto("url")
        .build();
  }

  @AfterEach
  public void tearDown() {
    repository.deleteAll();
  }

  @Test
  public void createBarang_success() throws Exception {
    mockMvc.perform(
        post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(request)))
        .andExpect(status().isOk());

    Barang result = repository.findAll().get(0);
    Assertions.assertEquals(request.getNama(), result.getNama());
    Assertions.assertEquals(request.getDeskripsi(), result.getDeskripsi());
    Assertions.assertEquals(request.getUrlFoto(), result.getUrlFoto());
  }

  @Test
  public void createBarang_failed_nameIsEmpty() throws Exception {
    request.setNama("");

    mockMvc.perform(
        post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(request)))
        .andExpect(status().isBadRequest());
  }

}

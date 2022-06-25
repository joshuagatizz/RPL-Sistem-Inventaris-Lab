package com.example.sisteminventarislab.barang;

import com.example.sisteminventarislab.SistemInventarisLabApplicationTests;
import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.repository.BarangRepository;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DeleteBarangTest extends SistemInventarisLabApplicationTests {

  @Autowired
  private BarangRepository repository;

  private static final String url = "/api/barang";
  private MockMvc mockMvc;
  private Barang savedData;

  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.savedData = Barang.builder().nama("barang1").deskripsi("deskripsi1").urlFoto("url1").build();
    repository.save(savedData);
  }

  @AfterAll
  public void tearDown() {
    repository.deleteAll();
  }

  @Test
  public void deleteBarang_success() throws Exception {
    mockMvc.perform(
        delete(url + "/" + savedData.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data", equalTo(Boolean.TRUE)));
  }

  @Test
  public void deleteBarang_failed_barangNotFound() throws Exception {
    mockMvc.perform(
        delete(url + "/" + "randomid"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)))
        .andExpect(jsonPath("$.errors[0]", equalTo(ErrorCode.BARANG_NOT_FOUND.getMessage())));
  }
}

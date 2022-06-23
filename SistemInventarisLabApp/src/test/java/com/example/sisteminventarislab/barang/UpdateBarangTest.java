package com.example.sisteminventarislab.barang;

import com.example.sisteminventarislab.SistemInventarisLabApplicationTests;
import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.repository.BarangRepository;
import com.example.sisteminventarislab.repository.UserRepository;
import com.example.sisteminventarislab.web.model.Request.UpdateBarangWebRequest;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class UpdateBarangTest extends SistemInventarisLabApplicationTests {
  @Autowired
  private BarangRepository repository;

  private static final String url = "/api/barang";
  private MockMvc mockMvc;
  private Barang savedData;
  private UpdateBarangWebRequest updateData;

  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.savedData = Barang.builder().nama("barang1").deskripsi("deskripsi1").urlFoto("url1").build();
    this.updateData = UpdateBarangWebRequest.builder().nama("barang2").deskripsi("deskripsi2").urlFoto("url2").idPeminjam("Bambank").build();
    repository.save(savedData);
  }

  @AfterEach
  public void tearDown() {
    repository.deleteAll();
  }

  @Test
  public void updateBarang_success() throws Exception {
    mockMvc.perform(
        put(url + "/" + savedData.getId())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(updateData)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    Barang result = repository.findAll().get(0);
    Assertions.assertEquals(updateData.getNama(), result.getNama());
    Assertions.assertEquals(updateData.getDeskripsi(), result.getDeskripsi());
    Assertions.assertEquals(updateData.getUrlFoto(), result.getUrlFoto());
    Assertions.assertEquals(updateData.getIdPeminjam(), result.getIdPeminjam());
  }

  @Test
  public void updateBarang_failed_dataNotFound() throws Exception {
    mockMvc.perform(
        put(url + "/" + "randomid")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(updateData)))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)))
        .andExpect(jsonPath("$.errors[0]", equalTo(ErrorCode.BARANG_NOT_FOUND.getMessage())));
  }

  @Test
  public void updateBarang_failed_nameIsEmpty() throws Exception {
    updateData.setNama("");

    mockMvc.perform(
        put(url + "/" + "randomid")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(updateData)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Nama barang tidak boleh kosong")));
  }
}

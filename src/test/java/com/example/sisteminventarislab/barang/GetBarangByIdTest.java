package com.example.sisteminventarislab.barang;

import com.example.sisteminventarislab.SistemInventarisLabApplicationTests;
import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.repository.BarangRepository;
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

public class GetBarangByIdTest extends SistemInventarisLabApplicationTests {

  @Autowired
  private BarangRepository repository;

  private static final String url = "/api/barang";
  private MockMvc mockMvc;
  private Barang savedData;

  @BeforeAll
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.savedData = Barang.builder().nama("barang1").deskripsi("deskripsi1").urlFoto("url1").build();

    repository.save(Barang.builder().nama("barang2").deskripsi("deskripsi2").urlFoto("url2").build());
    repository.save(savedData);
    repository.save(Barang.builder().nama("barang1").deskripsi("deskripsi1").urlFoto("url1").build());
  }

  @AfterAll
  public void tearDown() {
    repository.deleteAll();
  }

  @Test
  public void getBarangById_success() throws Exception {
    mockMvc.perform(
            get(url + "/" + savedData.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.data.id", equalTo(savedData.getId())))
        .andExpect(jsonPath("$.data.nama", equalTo(savedData.getNama())))
        .andExpect(jsonPath("$.data.deskripsi", equalTo(savedData.getDeskripsi())))
        .andExpect(jsonPath("$.data.urlFoto", equalTo(savedData.getUrlFoto())))
        .andExpect(jsonPath("$.data.idPeminjam", equalTo(savedData.getIdPeminjam())));
  }

  @Test
  public void getBarangById_failed_userNotFound() throws Exception {
    mockMvc.perform(
            get(url + "/" + "randomid"))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)))
        .andExpect(jsonPath("$.errors[0]", equalTo(ErrorCode.BARANG_NOT_FOUND.getMessage())));
  }
}

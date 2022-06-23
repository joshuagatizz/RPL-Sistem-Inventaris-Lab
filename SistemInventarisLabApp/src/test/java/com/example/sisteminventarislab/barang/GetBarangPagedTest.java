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

public class GetBarangPagedTest extends SistemInventarisLabApplicationTests {
  @Autowired
  private BarangRepository repository;

  private static final String url = "/api/barang";
  private MockMvc mockMvc;

  @BeforeAll
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    repository.save(Barang.builder().nama("barang1").deskripsi("deskripsi1").urlFoto("url1").build());
    repository.save(Barang.builder().nama("barang2").deskripsi("deskripsi2").urlFoto("url2").build());
    repository.save(Barang.builder().nama("barang3").deskripsi("deskripsi3").urlFoto("url3").build());
    repository.save(Barang.builder().nama("barang4").deskripsi("deskripsi4").urlFoto("url4").build());
    repository.save(Barang.builder().nama("barang5").deskripsi("deskripsi5").urlFoto("url5").build());
    repository.save(Barang.builder().nama("barang6").deskripsi("deskripsi6").urlFoto("url6").build());
    repository.save(Barang.builder().nama("barang7").deskripsi("deskripsi7").urlFoto("url7").build());
    repository.save(Barang.builder().nama("barang8").deskripsi("deskripsi8").urlFoto("url8").build());

  }

  @AfterAll
  void tearDown() {
    repository.deleteAll();
  }

  @Test
  public void getBarangPaged_success() throws Exception {
    mockMvc.perform(
            get(url).param("page", String.valueOf(1)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.data", IsCollectionWithSize.hasSize(4)));
  }

  @Test
  public void getBarangPaged_failed_invalidInput() throws Exception {
    mockMvc.perform(
            get(url).param("page", String.valueOf(-1)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)))
        .andExpect(jsonPath("$.errors[0]", equalTo(ErrorCode.INVALID_PAGE_INPUT.getMessage())));;
  }

  @Test
  public void getBarangPaged_failed_inputExceedsPageLimit() throws Exception {
    mockMvc.perform(
            get(url).param("page", String.valueOf(4)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.errors", IsCollectionWithSize.hasSize(1)))
        .andExpect(jsonPath("$.errors[0]", equalTo(ErrorCode.PAGE_LIMIT_EXCEEDED.getMessage())));
  }
}

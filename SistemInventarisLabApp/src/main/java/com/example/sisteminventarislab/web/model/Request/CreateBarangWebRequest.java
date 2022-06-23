package com.example.sisteminventarislab.web.model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBarangWebRequest {
  @NotEmpty(message = "URL foto tidak boleh kosong")
  String urlFoto;
  @NotEmpty(message = "Nama barang tidak boleh kosong")
  String nama;
  @NotEmpty(message = "Deskripsi barang tidak boleh kosong")
  String deskripsi;
}

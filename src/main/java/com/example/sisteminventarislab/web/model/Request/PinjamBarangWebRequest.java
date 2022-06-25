package com.example.sisteminventarislab.web.model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PinjamBarangWebRequest {
  @NotEmpty
  String idBarang;
  @NotEmpty
  String idPeminjam;
}

package com.example.sisteminventarislab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaporanMingguan {
  @Id
  private String id;
  private String idBarang;
  private String email;
  private String tipeLaporan;
  private Date tanggal;
}

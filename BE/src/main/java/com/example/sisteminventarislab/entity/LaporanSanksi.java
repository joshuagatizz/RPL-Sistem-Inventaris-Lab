package com.example.sisteminventarislab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaporanSanksi {
  @Id
  private String id;
  private String idBarang;
  private String email;
  private double denda;
}

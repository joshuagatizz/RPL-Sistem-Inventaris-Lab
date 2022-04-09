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
public class Barang {
  @Id
  private String id;
  private String nama;
  private String deskripsi;
  private int stock;
  private String urlGambar;
}

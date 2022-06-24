package com.example.sisteminventarislab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "barang")
public class Barang {
  @Id
  String id;
  String idPeminjam;
  String urlFoto;
  String nama;
  String deskripsi;
  String tanggalPinjam;
  String deadlineBalik;
  String tanggalBalik;
  boolean sudahBalik;
}

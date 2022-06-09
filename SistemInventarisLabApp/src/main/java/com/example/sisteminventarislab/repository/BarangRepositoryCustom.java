package com.example.sisteminventarislab.repository;

import com.example.sisteminventarislab.entity.Barang;

import java.util.List;

public interface BarangRepositoryCustom {
  List<Barang> getBarangPaged(int page);
}

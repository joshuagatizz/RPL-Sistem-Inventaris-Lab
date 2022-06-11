package com.example.sisteminventarislab.service;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.web.model.Request.CreateBarangWebRequest;
import com.example.sisteminventarislab.web.model.Request.UpdateBarangWebRequest;
import com.example.sisteminventarislab.web.model.Response.CreateBarangWebResponse;

import java.util.List;

public interface BarangService {
  CreateBarangWebResponse createBarang(CreateBarangWebRequest request);
  List<Barang> getAllBarang();
  List<Barang> getAllBarangPaged(int page);
  List<Barang> getBarangByUserId(String id);
  Barang updateBarang(String id, UpdateBarangWebRequest request);
  boolean deleteBarang(String id);
}

package com.example.sisteminventarislab.service;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.web.model.Request.CreateBarangWebRequest;
import com.example.sisteminventarislab.web.model.Request.PinjamBarangWebRequest;
import com.example.sisteminventarislab.web.model.Request.UpdateBarangWebRequest;
import com.example.sisteminventarislab.web.model.Response.CreateBarangWebResponse;

import java.util.List;

public interface BarangService {
  CreateBarangWebResponse createBarang(CreateBarangWebRequest request);
  List<Barang> getAllBarang();
  List<Barang> getAllBarangPaged(int page, boolean filterPinjam);
  Barang getBarangById(String id);
  List<Barang> getBarangByUserId(int page, String id);
  Barang pinjamBarang(PinjamBarangWebRequest request);
  Barang updateBarang(String id, UpdateBarangWebRequest request);
  Barang pinjamBarang(String idBarang, String idPeminjam);
  boolean deleteBarang(String id);
}

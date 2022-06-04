package com.example.sisteminventarislab.service;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.web.model.CreateBarangRequest;
import com.example.sisteminventarislab.web.model.UpdateBarangRequest;

import java.util.List;

public interface BarangService {
    Barang createBarang(CreateBarangRequest request);
    List<Barang> getAllBarang();
    List<Barang> getBarangByUserId(String id);
    Barang updateBarang(String id, UpdateBarangRequest request);
    boolean deleteBarang(String id);
}

package com.example.sisteminventarislab.service;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.web.model.Request.CreateBarangRequest;
import com.example.sisteminventarislab.web.model.Request.UpdateBarangRequest;
import com.example.sisteminventarislab.web.model.Response.CreateBarangResponse;

import java.util.List;

public interface BarangService {
    CreateBarangResponse createBarang(CreateBarangRequest request);
    List<Barang> getAllBarang();
    List<Barang> getBarangByUserId(String id);
    Barang updateBarang(String id, UpdateBarangRequest request);
    boolean deleteBarang(String id);
}

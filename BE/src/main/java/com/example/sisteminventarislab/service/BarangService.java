package com.example.sisteminventarislab.service;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.web.model.CreateUpdateBarangRequest;

import java.util.List;

public interface BarangService {
    Barang createBarang(CreateUpdateBarangRequest request);
    List<Barang> getAllBarang();
    Barang updateBarang(String id, CreateUpdateBarangRequest request);
    boolean deleteBarang(String id);
}

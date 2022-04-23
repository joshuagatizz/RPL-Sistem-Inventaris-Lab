package com.example.sisteminventarislab.service.impl;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.repository.BarangRepository;
import com.example.sisteminventarislab.service.BarangService;
import com.example.sisteminventarislab.web.model.CreateUpdateBarangRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarangServiceImpl implements BarangService {

    BarangRepository barangRepository;

    @Override
    public Barang createBarang(CreateUpdateBarangRequest request) {
        return barangRepository.save(Barang.builder()
                        .urlFoto(request.getUrlFoto())
                        .nama(request.getNama())
                        .deskripsi(request.getDeskripsi())
                        .stok(request.getStok())
                        .build());
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangRepository.findAll();
    }

    @Override
    public Barang updateBarang(String id, CreateUpdateBarangRequest request) {
        return barangRepository.save(barangRepository.findById(id).get().builder()
                        .urlFoto(request.getUrlFoto())
                        .nama(request.getNama())
                        .deskripsi(request.getDeskripsi())
                        .stok(request.getStok())
                        .build());
    }

    @Override
    public boolean deleteBarang(String id) {
        barangRepository.deleteById(id);
        return true;
    }
}

package com.example.sisteminventarislab.service.impl;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.repository.BarangRepository;
import com.example.sisteminventarislab.service.BarangService;
import com.example.sisteminventarislab.web.model.CreateBarangRequest;
import com.example.sisteminventarislab.web.model.UpdateBarangRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarangServiceImpl implements BarangService {

    @Autowired
    BarangRepository barangRepository;

    /**
     * Method createBarang merupakan method untuk membuat barang baru
     * dengan menerima request yang berisi detail barang yang akan dibuat
     *
     * Method ini memanfaatkan method copyProperties dari BeanUtils, di mana
     * method tersebut mengkopi semua nilai pada field yang ada pada kedua objek,
     * lalu memasukkan nilai kopian tersebut pada objek parameter ke-2 dari method
     * copyProperties
     *
     * @param request, detail barang yang akan dibuat
     * @return barang telah disimpan
     */
    @Override
    public Barang createBarang(CreateBarangRequest request) {
        Barang barang = Barang.builder().build();
        BeanUtils.copyProperties(request, barang);
        return barangRepository.save(barang);
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangRepository.findAll();
    }

    @Override
    public List<Barang> getBarangByUserId(String id) {
        return barangRepository.findBarangsByIdPeminjam(id);
    }

    @Override
    public Barang updateBarang(String id, UpdateBarangRequest request) {
        Barang barang = barangRepository.findById(id).get();
        BeanUtils.copyProperties(request, barang);
        return barangRepository.save(barang);
    }

    @Override
    public boolean deleteBarang(String id) {
        barangRepository.deleteById(id);
        return true;
    }
}

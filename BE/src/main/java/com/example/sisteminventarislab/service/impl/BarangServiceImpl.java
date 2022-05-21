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

    /**
     * Method updateBarang ini menerima id dari barang yang akan diperbaharui,
     * dan request berupa detail dari barang yang mana akan dioverwrite pada barang tersebut.
     * 
     * Method ini memanfaatkan method copyProperties dari BeanUtils, di mana secara singkat
     * method tersebut mengkopi semua nilai yang ada pada field yang ada pada kedua objek,
     * lalu menaruhkan nilai kopian tersebut pada objek yang diinputkan pada parameter
     * ke-2 dari method copyProperties, lalu menyimpannya ke dalam database menggunakan
     * repositori barang yaitu barangRepository.
     *
     * @param id, yaitu id dari barang yang akan diperbaharui.
     * @param request, detail dari barang yang akan dioverwrite.
     * @return Barang, yaitu hasil dari perbaharuan barang yang dilakukan .
     */
    @Override
    public Barang updateBarang(String id, UpdateBarangRequest request) {
        Barang barang = barangRepository.findById(id).get();
        BeanUtils.copyProperties(request, barang);
        return barangRepository.save(barang);
    }

    /**
     * Method deleteBarang pada class controller BarangController ini menerima id
     * dari barang yang akan dihapus.
     *
     * Method ini menggunakan repository barang yaitu barangRepository untuk menghapus
     * barang dengan input parameter id dari barang.
     *
     * @param id, yaitu id dari barang yang akan dihapus.
     * @return nilai boolean, yang menunjukkan keberhasilan dari penghapusan barang.
     */
    @Override
    public boolean deleteBarang(String id) {
        barangRepository.deleteById(id);
        return true;
    }
}

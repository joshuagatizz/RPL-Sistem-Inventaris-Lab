package com.example.sisteminventarislab.web.controller;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.service.BarangService;
import com.example.sisteminventarislab.web.model.CreateBarangRequest;
import com.example.sisteminventarislab.web.model.UpdateBarangRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(
        path = "/api/barang",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class BarangController {

    @Autowired
    BarangService barangService;

    @ApiOperation("create new Barang")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBarang(@RequestBody CreateBarangRequest request) {
        Barang barang = barangService.createBarang(request);
        return new ResponseEntity<>("Barang berhasil ditambah!", HttpStatus.OK);
    }

    @ApiOperation("get all Barang")
    @GetMapping
    public List<Barang> getAllBarang() {
        return barangService.getAllBarang();
    }

    /**
     * Method updateBarang pada class controller BarangController ini
     * menerima id dari barang yang akan diperbaharui, dan request berupa
     * detail dari barang yang mana akan dioverwrite pada barang tersebut.
     *
     * Method updateBarang pada controller ini akan memanfaatkan
     * implementasi dari method updateBarang pada class interface
     * service BarangService, yang mana diimplementasi pada class
     * BarangServiceImpl.
     *
     * @param id, yaitu id dari barang yang akan diperbaharui
     * @param request, detail dari barang yang akan dioverwrite
     * @return Barang, yaitu hasil dari perbaharuan barang yang dilakukan
     */
    @ApiOperation("update Barang by id")
    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Barang updateBarang(@PathVariable String id, @RequestBody UpdateBarangRequest request) {
        return barangService.updateBarang(id, request);
    }

    /**
     * Method deleteBarang pada class controller BarangController ini menerima id
     * dari barang yang akan dihapus.
     *
     * Method updateBarang pada controller ini akan memanfaatkan
     * implementasi dari method deleteBarang pada class interface
     * service BarangService, yang mana diimplementasi pada class
     * BarangServiceImpl.
     *
     * @param id, yaitu id dari barang yang akan dihapus.
     * @return nilai boolean, yang menunjukkan keberhasilan dari penghapusan barang.
     */
    @ApiOperation("delete Barang by id")
    @DeleteMapping(path = "/{id}")
    public boolean deleteBarang(@PathVariable String id) {
        return barangService.deleteBarang(id);
    }
}

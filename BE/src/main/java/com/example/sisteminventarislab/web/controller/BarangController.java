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

    /**
     * Method createBarang merupakan method untuk membuat barang baru
     * dengan menerima request berupa detail barang baru yang akan dibuat.
     *
     * Method ini memanfaatkan method createBarang dari class interface BarangService
     *
     * @param request, detail barang yang akan dibuat
     * @return response barang berhasil ditambah
     */
    @ApiOperation("create new Barang")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBarang(@RequestBody CreateBarangRequest request) {
        Barang barang = barangService.createBarang(request);
        return new ResponseEntity<>("Barang berhasil ditambah!", HttpStatus.OK);
    }

    /**
     * Method getAllBarang merupakan method untuk mendapatkan list yang
     * berisi semua barang. Method ini tidak memiliki parameter.
     *
     * Method ini memanfaatkan method getAllBarang dari class interface BarangService
     *
     * @return List<Barang>
     */
    @ApiOperation("get all Barang")
    @GetMapping
    public List<Barang> getAllBarang() {
        return barangService.getAllBarang();
    }

    @ApiOperation("update Barang by id")
    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Barang updateBarang(@PathVariable String id, @RequestBody UpdateBarangRequest request) {
        return barangService.updateBarang(id, request);
    }

    @ApiOperation("delete Barang by id")
    @DeleteMapping(path = "/{id}")
    public boolean deleteBarang(@PathVariable String id) {
        return barangService.deleteBarang(id);
    }
}

package com.example.sisteminventarislab.web.controller;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.service.BarangService;
import com.example.sisteminventarislab.web.model.CreateUpdateBarangRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(path = "/api/barang")
@RequiredArgsConstructor
public class BarangController {

    BarangService barangService;

    @ApiOperation("create new Barang")
    @PostMapping
    public Barang createBarang(CreateUpdateBarangRequest request) {
        return barangService.createBarang(request);
    }

    @ApiOperation("get all Barang")
    @GetMapping
    public List<Barang> getAllBarang() {
        return barangService.getAllBarang();
    }

    @ApiOperation("update Barang by id")
    @PutMapping(path = "/{id}")
    public Barang updateBarang(String id, CreateUpdateBarangRequest request) {
        return barangService.updateBarang(id, request);
    }

    @ApiOperation("delete Barang by id")
    @DeleteMapping(path = "/{id}")
    public boolean deleteBarang(String id) {
        return barangService.deleteBarang(id);
    }
}

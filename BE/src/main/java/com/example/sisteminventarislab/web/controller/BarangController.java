package com.example.sisteminventarislab.web.controller;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.service.BarangService;
import com.example.sisteminventarislab.web.model.CreateUpdateBarangRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public Barang createBarang(@RequestBody CreateUpdateBarangRequest request) {
        return barangService.createBarang(request);
    }

    @ApiOperation("get all Barang")
    @GetMapping
    public List<Barang> getAllBarang() {
        return barangService.getAllBarang();
    }

    @ApiOperation("update Barang by id")
    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Barang updateBarang(@PathVariable String id, @RequestBody CreateUpdateBarangRequest request) {
        return barangService.updateBarang(id, request);
    }

    @ApiOperation("delete Barang by id")
    @DeleteMapping(path = "/{id}")
    public boolean deleteBarang(@PathVariable String id) {
        return barangService.deleteBarang(id);
    }
}

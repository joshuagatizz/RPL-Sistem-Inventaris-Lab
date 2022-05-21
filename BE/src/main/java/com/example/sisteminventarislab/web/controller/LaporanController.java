package com.example.sisteminventarislab.web.controller;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.service.BarangService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
@RequestMapping(path = "/api/laporan", produces = MediaType.APPLICATION_JSON_VALUE)
public class LaporanController {

    @Autowired
    BarangService barangService;

    @ApiOperation("get Barang by User Id")
    @GetMapping(path = "/{id}")
    public List<Barang> getLaporanByUserId(@PathVariable String id) {
        return barangService.getBarangByUserId(id);
    }
}

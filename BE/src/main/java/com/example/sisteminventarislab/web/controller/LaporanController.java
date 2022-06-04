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

    /**
     * fungsi untuk mendapatkan List Barang berdasarkan suatu id User.
     * fungsi ini menerima 1 parameter yaitu String id dari user.
     * fungsi ini akan menggunakan method getBarangByUserId dari
     * barangService dan mengembalikan hasilnya.
     *
     * @param id Id dari user
     * @return List<Barang>, yaitu List Barang berdasarkan Id User
     */
    @ApiOperation("get Barang by User Id")
    @GetMapping(path = "/{id}")
    public List<Barang> getLaporanByUserId(@PathVariable String id) {
        return barangService.getBarangByUserId(id);
    }
}

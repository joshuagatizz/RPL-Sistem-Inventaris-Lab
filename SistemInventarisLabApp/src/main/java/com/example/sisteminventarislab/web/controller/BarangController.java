package com.example.sisteminventarislab.web.controller;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.entity.Response;
import com.example.sisteminventarislab.entity.helper.ResponseHelper;
import com.example.sisteminventarislab.service.BarangService;
import com.example.sisteminventarislab.web.model.Request.CreateBarangWebRequest;
import com.example.sisteminventarislab.web.model.Request.UpdateBarangWebRequest;
import com.example.sisteminventarislab.web.model.Response.CreateBarangWebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Api
@RestController
@RequestMapping(path = "/api/barang",
    produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BarangController {

  private final BarangService barangService;

  /**
   * Method createBarang merupakan method untuk membuat barang baru
   * dengan menerima request berupa detail barang baru yang akan dibuat.
   * Method ini memanfaatkan method createBarang dari class interface BarangService
   *
   * @param request, detail barang yang akan dibuat
   * @return response barang berhasil ditambah
   */
  @ApiOperation("create new Barang")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Response<CreateBarangWebResponse> createBarang(@RequestBody @Valid CreateBarangWebRequest request) {
    return ResponseHelper.ok(barangService.createBarang(request));
  }

  /**
   * Method getAllBarang merupakan method untuk mendapatkan list yang
   * berisi semua barang. Method ini tidak memiliki parameter.
   * Method ini memanfaatkan method getAllBarang dari class interface BarangService
   *
   * @return List<Barang>
   */
  @ApiOperation("get all Barang")
  @GetMapping("disabled-api")
  public Response<List<Barang>> getAllBarang() {
    return ResponseHelper.ok(barangService.getAllBarang());
  }

  @ApiOperation("get list Barang (size 4)")
  @GetMapping
  public Response<List<Barang>> getBarangListPaged(@Valid @NotEmpty(message = "Page tidak boleh kosong!") @Min(value = 1,
      message = "Page tidak boleh bernilai < 1!") int page) {
    return ResponseHelper.ok(barangService.getAllBarangPaged(page));
  }

  /**
   * Method updateBarang pada class controller BarangController ini
   * menerima id dari barang yang akan diperbaharui, dan request berupa
   * detail dari barang yang mana akan dioverwrite pada barang tersebut.
   * Method updateBarang pada controller ini akan memanfaatkan
   * implementasi dari method updateBarang pada class interface
   * service BarangService, yang mana diimplementasi pada class
   * BarangServiceImpl.
   *
   * @param id,      yaitu id dari barang yang akan diperbaharui
   * @param request, detail dari barang yang akan dioverwrite
   * @return Barang, yaitu hasil dari perbaharuan barang yang dilakukan
   */
  @ApiOperation("update Barang by id")
  @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
      consumes = MediaType.APPLICATION_JSON_VALUE,
      path = "/{id}")
  public Response<Barang> updateBarang(@PathVariable String id,
      @RequestBody @Valid UpdateBarangWebRequest request) {
    return ResponseHelper.ok(barangService.updateBarang(id, request));
  }

  /**
   * Method deleteBarang pada class controller BarangController ini menerima id
   * dari barang yang akan dihapus.
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
  public Response<Boolean> deleteBarang(@PathVariable String id) {
    return ResponseHelper.ok(barangService.deleteBarang(id));
  }
}

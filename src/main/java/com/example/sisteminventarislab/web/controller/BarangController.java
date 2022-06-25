package com.example.sisteminventarislab.web.controller;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.entity.Response;
import com.example.sisteminventarislab.entity.helper.ResponseHelper;
import com.example.sisteminventarislab.exception.CustomException;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.service.AccessTokenService;
import com.example.sisteminventarislab.service.BarangService;
import com.example.sisteminventarislab.web.model.Request.CreateBarangWebRequest;
import com.example.sisteminventarislab.web.model.Request.PinjamBarangWebRequest;
import com.example.sisteminventarislab.web.model.Request.UpdateBarangWebRequest;
import com.example.sisteminventarislab.web.model.Response.CreateBarangWebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Api
@RestController
@RequestMapping(path = "/api/barang")
@RequiredArgsConstructor
@CrossOrigin
public class BarangController {

  private final BarangService barangService;
  private final AccessTokenService accessTokenService;

  /**
   * Method createBarang merupakan method untuk membuat barang baru
   * dengan menerima request berupa detail barang baru yang akan dibuat.
   * Method ini memanfaatkan method createBarang dari class interface BarangService
   *
   * @param request, detail barang yang akan dibuat
   * @return response barang berhasil ditambah
   */
  @ApiOperation("create new Barang")
  @PostMapping
  public Response<CreateBarangWebResponse> createBarang(@RequestBody CreateBarangWebRequest request,
      @RequestParam String token) {
    if (!accessTokenService.doExist(token))
      throw new CustomException(ErrorCode.UNAUTHORIZED);

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

  @ApiOperation("get Barang by Id")
  @GetMapping(path = "/{id}")
  public Response<Barang> getBarangById(@PathVariable String id) {
    return ResponseHelper.ok(barangService.getBarangById(id));
  }

  @ApiOperation("get list Barang (size 4)")
  @GetMapping
  public Response<List<Barang>> getBarangListPaged(@RequestParam @Valid @NotEmpty(message = "Page tidak boleh kosong!") @Min(value = 1,
      message = "Page tidak boleh bernilai < 1!") Integer page, boolean filterPinjam) {
    return ResponseHelper.ok(barangService.getAllBarangPaged(page, filterPinjam));
  }

  @ApiOperation("get list Barang that is borrowed by user with idPeminjam")
  @GetMapping("/{idPeminjam}/pinjam")
  public Response<List<Barang>> getBarangDipinjam(@RequestParam int page, @PathVariable("idPeminjam") String idPeminjam) {
    return ResponseHelper.ok(barangService.getBarangByUserId(page, idPeminjam));
  }

  @ApiOperation("get list Barang that is borrowed by user with idPeminjam")
  @PostMapping("/{idPeminjam}/pinjam")
  public Response<Barang> getBarangDipinjam(@RequestBody @Valid PinjamBarangWebRequest request) {
    return ResponseHelper.ok(barangService.pinjamBarang(request));
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
      @RequestBody @Valid UpdateBarangWebRequest request, @RequestParam String token) {
    if (!accessTokenService.doExist(token))
      throw new CustomException(ErrorCode.UNAUTHORIZED);

    return ResponseHelper.ok(barangService.updateBarang(id, request));
  }

  @ApiOperation("Pinjam barang by id")
  @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
  public Response<Barang> pinjamBarang(@RequestParam String idBarang,
      @RequestParam String idPeminjam, @RequestParam String token) {
    if (!accessTokenService.doExist(token))
      throw new CustomException(ErrorCode.UNAUTHORIZED);

    return ResponseHelper.ok(barangService.pinjamBarang(idBarang, idPeminjam));
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
  public Response<Boolean> deleteBarang(@PathVariable String id, @RequestParam String token) {
    if (!accessTokenService.doExist(token))
      throw new CustomException(ErrorCode.UNAUTHORIZED);

    return ResponseHelper.ok(barangService.deleteBarang(id));
  }
}

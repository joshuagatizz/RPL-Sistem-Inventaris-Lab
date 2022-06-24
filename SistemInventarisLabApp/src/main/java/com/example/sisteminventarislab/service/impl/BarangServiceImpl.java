package com.example.sisteminventarislab.service.impl;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.exception.CustomException;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.repository.BarangRepository;
import com.example.sisteminventarislab.repository.BarangRepositoryCustom;
import com.example.sisteminventarislab.service.BarangService;
import com.example.sisteminventarislab.web.model.Request.CreateBarangWebRequest;
import com.example.sisteminventarislab.web.model.Request.PinjamBarangWebRequest;
import com.example.sisteminventarislab.web.model.Request.UpdateBarangWebRequest;
import com.example.sisteminventarislab.web.model.Response.CreateBarangWebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BarangServiceImpl implements BarangService {

  private final BarangRepository barangRepository;
  private final BarangRepositoryCustom barangRepositoryCustom;

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
  public CreateBarangWebResponse createBarang(CreateBarangWebRequest request) {
    Barang barang = new Barang();
    BeanUtils.copyProperties(request, barang);
    barang = barangRepository.save(barang);
    return CreateBarangWebResponse
        .builder()
        .status("Sukses")
        .barang(barang)
        .build();
  }

  @Override
  public List<Barang> getAllBarang() {
    return barangRepository.findAll();
  }

  @Override
  public List<Barang> getAllBarangPaged(int page, boolean filterPinjam) {
    if (page <= 0)
      throw new CustomException(ErrorCode.INVALID_PAGE_INPUT);
    List<Barang> listBarang = barangRepositoryCustom.getBarangPaged(page, filterPinjam);
    if (ObjectUtils.isEmpty(listBarang))
      throw new CustomException(ErrorCode.PAGE_LIMIT_EXCEEDED);
    return listBarang;
  }

  @Override
  public Barang getBarangById(String id) {
    Barang barang = barangRepository.findById(id).orElse(null);
    if (ObjectUtils.isEmpty(barang))
      throw new CustomException(ErrorCode.BARANG_NOT_FOUND);
    return barang;
  }

  /**
   * fungsi untuk mendapatkan List Barang berdasar Id Peminjam dari DB.
   * fungsi ini menerima parameter id dari user dan kemudian akan mengakses DB
   * menggunakan barangRepository untuk melakukan query dan mengembalikan hasilnya.
   *
   * @param id Id dari user
   * @return List<Barang>, yaitu List barang berdasarkan Id Peminjam
   */
  @Override
  public List<Barang> getBarangByUserId(int page, String id) {
    if (page <= 0)
      throw new CustomException(ErrorCode.INVALID_PAGE_INPUT);
    List<Barang> listBarang = barangRepositoryCustom.findBarangsByIdPeminjam(page, id);
    if (ObjectUtils.isEmpty(listBarang))
      throw new CustomException(ErrorCode.PAGE_LIMIT_EXCEEDED);
    return listBarang;
  }

  @Override
  public Barang pinjamBarang(PinjamBarangWebRequest request) {
    Barang barang = barangRepository.findById(request.getIdBarang()).orElse(null);
    if (ObjectUtils.isEmpty(barang))
      throw new CustomException(ErrorCode.BARANG_NOT_FOUND);
    barang.setIdPeminjam(request.getIdPeminjam());
    return barangRepository.save(barang);
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
  public Barang updateBarang(String id, UpdateBarangWebRequest request) {
    Barang barang = barangRepository.findById(id).orElse(null);
    if (ObjectUtils.isEmpty(barang))
      throw new CustomException(ErrorCode.BARANG_NOT_FOUND);
    BeanUtils.copyProperties(request, barang);
    return barangRepository.save(barang);
  }

  @Override
  public Barang pinjamBarang(String idBarang, String idPeminjam) {
    Barang barang = barangRepository.findById(idBarang).orElse(null);
    if (ObjectUtils.isEmpty(barang))
      throw new CustomException(ErrorCode.BARANG_NOT_FOUND);
    barang.setIdPeminjam(idPeminjam);
    String pattern = "yyyy/MM/dd";
    DateFormat df = new SimpleDateFormat(pattern);
    Date today = new Date();
    String todayAsString = df.format(today);
    barang.setTanggalPinjam(todayAsString);
    Date oneWeek = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(7, TimeUnit.DAYS));
    barang.setDeadlineBalik(df.format(oneWeek));
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
    if (ObjectUtils.isEmpty(barangRepository.findById(id))) {
      throw new CustomException(ErrorCode.BARANG_NOT_FOUND);
    }
    barangRepository.deleteById(id);
    return true;
  }
}

package com.example.sisteminventarislab.repository;

import com.example.sisteminventarislab.entity.Barang;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarangRepository extends MongoRepository<Barang, String> {
  List<Barang> findBarangsByIdPeminjam(String id);
}

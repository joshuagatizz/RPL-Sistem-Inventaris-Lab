package com.example.sisteminventarislab.repository;

import com.example.sisteminventarislab.entity.Barang;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangRepository extends MongoRepository<Barang, String> {
}

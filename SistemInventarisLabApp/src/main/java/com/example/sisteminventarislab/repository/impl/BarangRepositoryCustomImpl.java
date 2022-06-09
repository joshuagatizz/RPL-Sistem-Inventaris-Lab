package com.example.sisteminventarislab.repository.impl;

import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.repository.BarangRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarangRepositoryCustomImpl implements BarangRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public List<Barang> getBarangPaged(int page) {
    Query query = new Query();
    query.with(PageRequest.of(page-1, 4));
    return mongoTemplate.find(query, Barang.class);
  }
}

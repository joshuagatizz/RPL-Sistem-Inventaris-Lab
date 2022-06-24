package com.example.sisteminventarislab.repository.impl;

import com.example.sisteminventarislab.constant.BarangFieldname;
import com.example.sisteminventarislab.entity.Barang;
import com.example.sisteminventarislab.repository.BarangRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarangRepositoryCustomImpl implements BarangRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public List<Barang> getBarangPaged(int page, boolean filter) {
    Query query = new Query();

    if (ObjectUtils.isEmpty(filter) || !filter) {
      query
          .addCriteria(Criteria.where(BarangFieldname.ID_PEMINJAM).is(null))
          .with(PageRequest.of(page - 1, 4));
    } else {
      query.with(PageRequest.of(page - 1, 4));
    }
    return mongoTemplate.find(query, Barang.class);
  }

  @Override
  public List<Barang> findBarangsByIdPeminjam(int page, String id) {
    Query query = new Query();
    query
        .addCriteria(Criteria.where(BarangFieldname.ID_PEMINJAM).is(id))
        .with(PageRequest.of(page - 1, 4));
    return mongoTemplate.find(query, Barang.class);
  }
}

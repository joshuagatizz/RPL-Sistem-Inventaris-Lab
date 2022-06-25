package com.example.sisteminventarislab.repository.impl;

import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.repository.UserRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public List<User> getUserPaged(int page) {
    Query query = new Query();
    query.with(PageRequest.of(page-1, 4));
    return mongoTemplate.find(query, User.class);
  }
}

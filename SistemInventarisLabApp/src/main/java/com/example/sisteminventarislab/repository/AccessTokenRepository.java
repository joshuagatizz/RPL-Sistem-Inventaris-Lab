package com.example.sisteminventarislab.repository;

import com.example.sisteminventarislab.entity.AccessToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends MongoRepository<AccessToken, String> {
  AccessToken findByUser_Email(String email);
}

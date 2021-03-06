package com.example.sisteminventarislab.repository;

import com.example.sisteminventarislab.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  User findByEmail(String email);
  User getUserByNimEquals(String nim);
}

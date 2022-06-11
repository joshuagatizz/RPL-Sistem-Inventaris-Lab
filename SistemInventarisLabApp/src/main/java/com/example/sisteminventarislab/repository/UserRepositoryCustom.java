package com.example.sisteminventarislab.repository;

import com.example.sisteminventarislab.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
  List<User> getUserPaged(int page);
}

package com.example.sisteminventarislab.service;

import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.web.model.Request.CreateUserWebRequest;
import com.example.sisteminventarislab.web.model.Request.UpdateUserWebRequest;

import java.util.List;

public interface UserService {
  User createUser(CreateUserWebRequest request);
  List<User> getAllUser();
  List<User> getUsersPaged(int page);
  User getUserById(String id);
  User updateUser(String id, UpdateUserWebRequest request);
  boolean deleteUser(String id);
}

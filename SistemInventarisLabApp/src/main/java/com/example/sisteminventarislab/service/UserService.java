package com.example.sisteminventarislab.service;

import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.web.model.Request.CreateUpdateUserWebRequest;

import java.util.List;

public interface UserService {
  User createUser(CreateUpdateUserWebRequest request);
  List<User> getAllUser();
  List<User> getUsersPaged(int page);
  User updateUser(String id, CreateUpdateUserWebRequest request);
  boolean deleteUser(String id);
}

package com.example.sisteminventarislab.service;

import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.web.model.CreateUpdateUserRequest;

import java.util.List;

public interface UserService {
    User createUser(CreateUpdateUserRequest request);
    List<User> getAllUser();
    User updateUser(String id, CreateUpdateUserRequest request);
    boolean deleteUser(String id);
}

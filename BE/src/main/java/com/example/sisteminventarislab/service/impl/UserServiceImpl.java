package com.example.sisteminventarislab.service.impl;

import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.repository.UserRepository;
import com.example.sisteminventarislab.service.UserService;
import com.example.sisteminventarislab.web.model.CreateUpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User createUser(CreateUpdateUserRequest request) {
        return userRepository.save(User.builder()
                .email(request.getEmail())
                .nama(request.getNama())
                .password(request.getPassword())
                .tipeUser(request.getTipeUser())
                .build());
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(String id, CreateUpdateUserRequest request) {
        return userRepository.save(userRepository.findById(id).get().builder()
                .email(request.getEmail())
                .nama(request.getNama())
                .password(request.getPassword())
                .tipeUser(request.getTipeUser())
                .build());
    }

    @Override
    public boolean deleteUser(String id) {
        userRepository.deleteById(id);
        return true;
    }
}

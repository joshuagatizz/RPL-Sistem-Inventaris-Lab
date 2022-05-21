package com.example.sisteminventarislab.service.impl;

import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.repository.UserRepository;
import com.example.sisteminventarislab.service.UserService;
import com.example.sisteminventarislab.web.model.CreateUpdateUserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(CreateUpdateUserRequest request) {
        User user = User.builder().build();
        BeanUtils.copyProperties(request, user);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(String id, CreateUpdateUserRequest request) {
        User user = userRepository.findById(id).get();
        BeanUtils.copyProperties(request, user);
        return userRepository.save(user);
    }

    /**
     * Method deleteUser pada class controller UserController ini menerima id
     * dari data user yang akan dihapus
     *
     * Method ini menggunakan repository UserRepository untuk menghapus user
     * dengan input parameter id user
     *
     * @param id (id dari user yang akan dihapus)
     * @return Boolean (true apabila data user berhasil dihapus, false apabila data user gagal dihapus)
     */
    @Override
    public boolean deleteUser(String id) {
        userRepository.deleteById(id);
        return true;
    }
}

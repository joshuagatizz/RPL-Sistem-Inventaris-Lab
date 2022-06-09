package com.example.sisteminventarislab.service.impl;

import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.exception.CustomException;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.repository.UserRepository;
import com.example.sisteminventarislab.repository.UserRepositoryCustom;
import com.example.sisteminventarislab.service.UserService;
import com.example.sisteminventarislab.web.model.Request.CreateUpdateUserWebRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


  private final UserRepository userRepository;
  private final UserRepositoryCustom userRepositoryCustom;

  @Override
  public User createUser(CreateUpdateUserWebRequest request) {
    User user = User.builder().build();
    BeanUtils.copyProperties(request, user);
    return userRepository.save(user);
  }

  /**
   * fungsi untuk mendapatkan List semua User dari DB.
   * fungsi ini akan menggunakan userRepository untuk melakukan query ke DB
   * dan mengembalikan hasilnya.
   *
   * @return List<User>, yaitu List dari semua user
   */
  @Override
  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  @Override
  public List<User> getUsersPaged(int page) {
    return userRepositoryCustom.getUserPaged(page);
  }

  @Override
  public User updateUser(String id, CreateUpdateUserWebRequest request) {
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
    if (ObjectUtils.isEmpty(userRepository.findById(id))) {
      throw new CustomException(ErrorCode.USER_NOT_FOUND);
    }
    userRepository.deleteById(id);
    return true;
  }
}

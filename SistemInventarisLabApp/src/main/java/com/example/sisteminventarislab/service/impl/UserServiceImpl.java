package com.example.sisteminventarislab.service.impl;

import com.example.sisteminventarislab.entity.AccessToken;
import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.exception.CustomException;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.repository.UserRepository;
import com.example.sisteminventarislab.repository.UserRepositoryCustom;
import com.example.sisteminventarislab.service.AccessTokenService;
import com.example.sisteminventarislab.service.UserService;
import com.example.sisteminventarislab.web.model.Request.CreateUserWebRequest;
import com.example.sisteminventarislab.web.model.Request.UpdateUserWebRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


  private final UserRepository userRepository;
  private final UserRepositoryCustom userRepositoryCustom;
  private final AccessTokenService accessTokenService;

  @Override
  public User createUser(CreateUserWebRequest request) {
    if (nimIsTaken(request.getNim()))
      throw new CustomException(ErrorCode.USER_NIM_ALREADY_EXISTS);
    User user = User.builder().build();
    BeanUtils.copyProperties(request, user);
    return userRepository.save(user);
  }

  private boolean nimIsTaken(String nim) {
    User user = userRepository.getUserByNimEquals(nim);
    return !ObjectUtils.isEmpty(user);
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
    if (page <= 0)
      throw new CustomException(ErrorCode.INVALID_PAGE_INPUT);
    List<User> listUser = userRepositoryCustom.getUserPaged(page);
    if (ObjectUtils.isEmpty(listUser))
      throw new CustomException(ErrorCode.PAGE_LIMIT_EXCEEDED);
    return listUser;
  }

  @Override
  public User getUserById(String id) {
    User user = userRepository.findById(id).orElse(null);
    if (ObjectUtils.isEmpty(user))
      throw new CustomException(ErrorCode.USER_NOT_FOUND);
    return user;
  }

  @Override
  public User updateUser(String id, UpdateUserWebRequest request) {
    User user = userRepository.findById(id).orElse(null);
    if (ObjectUtils.isEmpty(user))
      throw new CustomException(ErrorCode.USER_NOT_FOUND);
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

  @Override
  public AccessToken login(String email, String password) {
    User user = userRepository.findByEmail(email);
    if (ObjectUtils.isEmpty(user) || !user.getPassword().equals(password))
      throw new CustomException(ErrorCode.WRONG_EMAIL_PASSWORD);
    return accessTokenService.createToken((user.getTipeUser().equals("ADMIN") ? 1 : 0), user);
  }

  @Override
  public void logout(String token) {
    accessTokenService.deleteToken(token);
  }
}

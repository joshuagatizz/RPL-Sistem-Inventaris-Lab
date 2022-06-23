package com.example.sisteminventarislab.service.impl;

import com.example.sisteminventarislab.entity.AccessToken;
import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.exception.CustomException;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.repository.AccessTokenRepository;
import com.example.sisteminventarislab.service.AccessTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class AccessTokenServiceImpl implements AccessTokenService {

  private final AccessTokenRepository accessTokenRepository;

  @Override
  public AccessToken createToken(int access, User user) {

    AccessToken accessToken = accessTokenRepository.findByUser_Email(user.getEmail());

    if (!ObjectUtils.isEmpty(accessToken))
      return accessToken;

    accessToken = AccessToken.builder().user(new AccessToken.User()).access(access).build();
    BeanUtils.copyProperties(user, accessToken.getUser());
    return accessTokenRepository.save(accessToken);
  }

  @Override
  public AccessToken getToken(String token) {
    return accessTokenRepository.findById(token)
        .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_NOT_FOUND));
  }

  @Override
  public boolean deleteToken(String token) {
    getToken(token);
    accessTokenRepository.deleteById(token);
    return true;
  }

  @Override
  public boolean doExist(String token) {
    return accessTokenRepository.findById(token).isPresent();
  }
}

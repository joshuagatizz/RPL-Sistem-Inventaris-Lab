package com.example.sisteminventarislab.service.impl;

import com.example.sisteminventarislab.entity.AccessToken;
import com.example.sisteminventarislab.exception.CustomException;
import com.example.sisteminventarislab.exception.ErrorCode;
import com.example.sisteminventarislab.repository.AccessTokenRepository;
import com.example.sisteminventarislab.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

  @Autowired
  AccessTokenRepository accessTokenRepository;

  @Override
  public AccessToken createToken() {
    AccessToken token = AccessToken.builder().build();
    return accessTokenRepository.save(token);
  }

  @Override
  public AccessToken getToken(String token) {
    if (ObjectUtils.isEmpty(accessTokenRepository.findById(token))) {
      throw new CustomException(ErrorCode.TOKEN_NOT_FOUND);
    }
    return accessTokenRepository.findById(token).get();
  }

  @Override
  public boolean deleteToken(String token) {
    if (ObjectUtils.isEmpty(accessTokenRepository.findById(token))) {
      throw new CustomException(ErrorCode.TOKEN_NOT_FOUND);
    }
    accessTokenRepository.deleteById(token);
    return true;
  }
}

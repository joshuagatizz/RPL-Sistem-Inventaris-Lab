package com.example.sisteminventarislab.service;

import com.example.sisteminventarislab.entity.AccessToken;
import com.example.sisteminventarislab.entity.User;

public interface AccessTokenService {
  AccessToken createToken(int access, User user);
  AccessToken getToken(String token);
  boolean deleteToken(String token);
  boolean doExist(String token);
}

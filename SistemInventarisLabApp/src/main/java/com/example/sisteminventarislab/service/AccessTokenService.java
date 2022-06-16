package com.example.sisteminventarislab.service;

import com.example.sisteminventarislab.entity.AccessToken;

public interface AccessTokenService {
  AccessToken createToken();
  AccessToken getToken(String token);
  boolean deleteToken(String token);
}

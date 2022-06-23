package com.example.sisteminventarislab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "access_token")
public class AccessToken {
  @Id
  String token;
  int access;
  User user;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class User {
    String email;
    String nama;
    String password;
    String tipeUser;
  }
}

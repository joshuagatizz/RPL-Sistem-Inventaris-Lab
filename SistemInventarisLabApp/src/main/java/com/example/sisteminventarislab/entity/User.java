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
@Document(collection = "user")
public class User {
  @Id
  String id;
  String email;
  String nama;
  String nim;
  String password;
  String tipeUser;
  String imgUrl;
}

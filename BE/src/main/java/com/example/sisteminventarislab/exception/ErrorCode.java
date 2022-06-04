package com.example.sisteminventarislab.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
  BARANG_NOT_FOUND(HttpStatus.NOT_FOUND, "Barang tidak ditemukan");
  HttpStatus httpStatus;
  String message;
}

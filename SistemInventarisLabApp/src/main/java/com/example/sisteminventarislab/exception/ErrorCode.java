package com.example.sisteminventarislab.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User tidak ditemukan"),
  BARANG_NOT_FOUND(HttpStatus.NOT_FOUND, "Barang tidak ditemukan"),
  TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Token tidak ditemukan"),
  PAGE_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "Halaman yang anda kunjungi melebihi batas limit halaman"),
  UNAUTHORIZED(HttpStatus.BAD_REQUEST, "Anda tidak memiliki akses"),
  WRONG_EMAIL_PASSWORD(HttpStatus.BAD_REQUEST, "Email atau password salah");
  HttpStatus httpStatus;
  String message;
}

package com.example.sisteminventarislab.web.controller;

import com.example.sisteminventarislab.entity.Response;
import com.example.sisteminventarislab.entity.helper.ResponseHelper;
import com.example.sisteminventarislab.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ErrorController {

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<Response<Object>> handleHibernateValidation(MethodArgumentNotValidException ex) {
    List<String> errorMsg = new ArrayList();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      errorMsg.add(error.getDefaultMessage());
    });

    Response responseBody = ResponseHelper.badRequest();
    responseBody.setErrors(errorMsg);

    return ResponseEntity
        .status(400)
        .body(responseBody);
  }

  @ExceptionHandler({CustomException.class})
  public ResponseEntity<Response<Object>> handleCustomException(CustomException ex) {
    Response responseBody = Response
        .builder()
        .code(ex.getErrorCode().getHttpStatus().value())
        .status(ex.getErrorCode().getHttpStatus().name())
        .errors(Arrays.asList(ex.getErrorCode().getMessage()))
        .build();

    return ResponseEntity
        .status(ex.getErrorCode().getHttpStatus())
        .body(responseBody);
  }

}

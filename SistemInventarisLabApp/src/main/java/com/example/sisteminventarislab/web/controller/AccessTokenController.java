package com.example.sisteminventarislab.web.controller;

import com.example.sisteminventarislab.entity.AccessToken;
import com.example.sisteminventarislab.entity.Response;
import com.example.sisteminventarislab.entity.helper.ResponseHelper;
import com.example.sisteminventarislab.service.AccessTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/access-token", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccessTokenController {

//  private final AccessTokenService accessTokenService;
//
//  @ApiOperation("Generate new token")
//  @PostMapping
//  public Response<AccessToken> createToken() {
//    return ResponseHelper.ok(accessTokenService.createToken());
//  }
//
//  @ApiOperation("Get token")
//  @GetMapping(path = "/{token}")
//  public Response<AccessToken> getToken(@PathVariable String token) {
//    return ResponseHelper.ok(accessTokenService.getToken(token));
//  }
//
//  @ApiOperation("Delete Token")
//  @DeleteMapping(path = "/{token}")
//  public Response<Boolean> deleteToken(@PathVariable String token) {
//    return ResponseHelper.ok(accessTokenService.deleteToken(token));
//  }
}

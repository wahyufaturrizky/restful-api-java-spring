package com.springboottest.restfulapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.LoginUserRequest;
import com.springboottest.restfulapi.model.Response;
import com.springboottest.restfulapi.model.TokenResponse;
import com.springboottest.restfulapi.service.AuthService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping(path = "/api/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<TokenResponse> login(@RequestBody LoginUserRequest request) {
    TokenResponse tokenResponse = authService.login(request);
    return Response.<TokenResponse>builder().data(tokenResponse).build();
  }

  @DeleteMapping(path = "/api/auth/logout", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<String> logout(User user) {
    authService.logout(user);
    return Response.<String>builder().data("OK").build();
  }
  
}

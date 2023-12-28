package com.springboottest.restfulapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import com.springboottest.restfulapi.model.RegisterUserRequest;
import com.springboottest.restfulapi.model.Response;
import com.springboottest.restfulapi.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {

  @Autowired
  private UserService userService;
  
  @PostMapping(
    path = "/api/users",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Response<String> register(@RequestBody RegisterUserRequest request) {
    userService.register(request);
    return Response.<String>builder().data("OK").build();
  }
  
  
}

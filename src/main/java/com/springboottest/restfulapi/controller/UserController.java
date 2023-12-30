package com.springboottest.restfulapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.RegisterUserRequest;
import com.springboottest.restfulapi.model.Response;
import com.springboottest.restfulapi.model.UpdateUserRequest;
import com.springboottest.restfulapi.model.UserResponse;
import com.springboottest.restfulapi.service.UserService;



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

  @GetMapping(path = "api/users/current", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<UserResponse> get(User user) {
    UserResponse userResponse = userService.get(user);
    return Response.<UserResponse>builder().data(userResponse).build();
  }
  
  @PatchMapping(path = "api/users/current", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<UserResponse> update(User user, @RequestBody UpdateUserRequest request) {
    UserResponse userResponse = userService.update(user, request);

    return Response.<UserResponse>builder().data(userResponse).build();
  }
  
  
}

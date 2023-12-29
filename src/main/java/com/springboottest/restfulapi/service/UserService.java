package com.springboottest.restfulapi.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.RegisterUserRequest;
import com.springboottest.restfulapi.model.UpdateUserRequest;
import com.springboottest.restfulapi.model.UserResponse;
import com.springboottest.restfulapi.repository.UserRepository;
import com.springboottest.restfulapi.security.BCrypt;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ValidationService validationService;

  @Transactional
  public void register(RegisterUserRequest request) {
    validationService.validate(request);

    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
    user.setName(request.getName());

    userRepository.save(user);
  }

  public UserResponse get(User user) {
    return UserResponse.builder().username(user.getUsername()).name(user.getName()).build();
  }

  @Transactional
  public UserResponse update(User user, UpdateUserRequest request) {
    validationService.validate(request);

    if (Objects.nonNull(request.getName())) {
      user.setName(request.getName());
    }

    if (Objects.nonNull(request.getPassword())) {
      user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
    }

    userRepository.save(user);

    return UserResponse.builder().name(user.getName()).username(user.getUsername()).build();
  }
  
}

package com.springboottest.restfulapi.service;

import java.util.Set;

import javax.xml.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboottest.exception.ApiException;
import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.RegisterUserRequest;
import com.springboottest.security.BCrypt;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  private Validator userValidator;

  public void register(RegisterUserRequest request) {
    Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = userValidator.validate(request);

    if (constraintViolations.size() != 0) {
      throw new ApiException("Username already registered");
    }

    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
    user.setName(request.getName());
    
    userRepository.save(user);
  }
  
}

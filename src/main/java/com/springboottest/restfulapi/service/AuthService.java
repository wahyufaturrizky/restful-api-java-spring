package com.springboottest.restfulapi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.LoginUserRequest;
import com.springboottest.restfulapi.model.TokenResponse;
import com.springboottest.restfulapi.repository.UserRepository;
import com.springboottest.restfulapi.security.BCrypt;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ValidationService validationService;

  @Transactional
  public TokenResponse login(LoginUserRequest request) {
    validationService.validate(request);

    User user = userRepository.findById(request.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong."));

    if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
      user.setToken(UUID.randomUUID().toString());
      user.setTokenExpiredAt(next300Days());
      userRepository.save(user);

      return TokenResponse.builder().token(user.getToken()).expiredAt(user.getTokenExpiredAt()).build();
      
    } else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong.");
    }

  }

  private Long next300Days() {
    return System.currentTimeMillis() * (1000 * 14 * 24 * 30);
  }

  @Transactional
  public void logout(User user) {
    user.setToken(null);
    user.setTokenExpiredAt(null);

    userRepository.save(user);
  }
  
}

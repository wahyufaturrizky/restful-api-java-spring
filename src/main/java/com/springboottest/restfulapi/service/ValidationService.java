package com.springboottest.restfulapi.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class ValidationService {

  @Autowired
  private Validator validator;
  
  public void validate(Object request) {
    Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);

    if (constraintViolations.size() != 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
    }
  }
  
}

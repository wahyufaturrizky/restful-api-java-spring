package com.springboottest.restfulapi.exception;

public class ApiException extends RuntimeException {

  public ApiException(String message) {
    super(message);
  }
  
}

package com.springboottest.restfulapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springboottest.restfulapi.resolver.UserArgumentResolver;

public class WebConfiguration implements WebMvcConfigurer {

  @Autowired
  private UserArgumentResolver userArgumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    resolvers.add(userArgumentResolver);
  }
  
  
}

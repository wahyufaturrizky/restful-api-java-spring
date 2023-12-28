package com.springboottest.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.springboottest.restfulapi.entity.User;

@Repository
public interface UserRepository extends JpaRepositoryExtension<User, String> {
  
}

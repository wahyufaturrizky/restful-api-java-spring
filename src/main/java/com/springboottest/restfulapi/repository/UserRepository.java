package com.springboottest.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboottest.restfulapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  
}

package com.springboottest.restfulapi.repository;

import org.springframework.stereotype.Repository;

import com.springboottest.restfulapi.entity.Contacts;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public class ContactRepository extends JpaRepository<Contacts, String> {
  
}

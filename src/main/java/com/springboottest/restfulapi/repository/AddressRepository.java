package com.springboottest.restfulapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboottest.restfulapi.entity.Address;
import com.springboottest.restfulapi.entity.Contacts;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

  Optional<Address> findFirstByContactAndId(Contacts contacts, String contactId);
  
}

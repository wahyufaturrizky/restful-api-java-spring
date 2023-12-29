package com.springboottest.restfulapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboottest.restfulapi.entity.Contacts;
import com.springboottest.restfulapi.entity.User;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, String> {

  Optional<Contacts> findFirstByUserAndId(User user, String id);
  
}

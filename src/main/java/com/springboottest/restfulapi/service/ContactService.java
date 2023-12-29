package com.springboottest.restfulapi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboottest.restfulapi.entity.Contacts;
import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.ContactResponse;
import com.springboottest.restfulapi.model.CreateContactRequest;
import com.springboottest.restfulapi.repository.ContactRepository;

import jakarta.transaction.Transactional;

@Service
public class ContactService {

  @Autowired
  private ContactRepository contactRepository;

  @Autowired
  private ValidationService validationService;

  @Transactional
  public ContactResponse create(User user, CreateContactRequest request) {
    validationService.validate(user, request);

    Contacts contacts = new Contacts();
    contacts.setId(UUID.randomUUID().toString());
    contacts.setFirstName(request.getFirstName());
    contacts.setLastName(request.getLastName());
    contacts.setEmail(request.getEmail());
    contacts.setPhone(request.getPhone());
    contacts.setUser(user);
    
    contactRepository.save(contacts);

    return ContactResponse.builder().id(contacts.getId()).firstName(contacts.getFirstName()).lastName(contacts.getLastName()).email(contacts.getEmail()).phone(contacts.getPhone()).builder();

  }
  
}

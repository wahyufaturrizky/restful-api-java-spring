package com.springboottest.restfulapi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboottest.restfulapi.entity.Contacts;
import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.ContactResponse;
import com.springboottest.restfulapi.model.CreateContactRequest;
import com.springboottest.restfulapi.model.UpdateContactRequest;
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
    validationService.validate(request);

    Contacts contacts = new Contacts();
    contacts.setId(UUID.randomUUID().toString());
    contacts.setFirstName(request.getFirstName());
    contacts.setLastName(request.getLastName());
    contacts.setEmail(request.getEmail());
    contacts.setPhone(request.getPhone());
    contacts.setUser(user);
    
    contactRepository.save(contacts);

    return toContactResponse(contacts);

  }

  private ContactResponse toContactResponse(Contacts contacts) {
    return ContactResponse.builder().id(contacts.getId()).firstName(contacts.getFirstName()).lastName(contacts.getLastName()).email(contacts.getEmail()).phone(contacts.getPhone()).build();
  }

  @Transactional(readOnly = true)
  public ContactResponse get(User user, String id) {
    Contacts contacts = contactRepository.findFirstByUserAndId(user, id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contacts not found"));

    return toContactResponse(contacts);
  }

  @Transactional
  public ContactResponse update(User user, UpdateContactRequest request) {
    Contacts contacts = contactRepository.findFirstByUserAndId(user, request.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

    contacts.setFirstName(request.getFirstName());
    contacts.setLastName(request.getLastName());
    contacts.setEmail(request.getEmail());
    contacts.setPhone(request.getPhone());
    contactRepository.save(contacts);

    return toContactResponse(contacts);
  }
  
}

package com.springboottest.restfulapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.ContactResponse;
import com.springboottest.restfulapi.model.CreateContactRequest;
import com.springboottest.restfulapi.model.Response;
import com.springboottest.restfulapi.service.ContactService;

@RestController
public class ContactController {

  @Autowired
  private ContactService contactService;
  
  @PostMapping(path = "/api/contacts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<ContactResponse> create(User user, @RequestBody CreateContactRequest request) {
    ContactResponse contactResponse = contactService.create(user, request);

    return Response.<ContactResponse>builder().data(contactResponse).build();

  }
  
}

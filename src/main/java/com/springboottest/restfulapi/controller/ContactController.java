package com.springboottest.restfulapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.ContactResponse;
import com.springboottest.restfulapi.model.CreateContactRequest;
import com.springboottest.restfulapi.model.PagingResponse;
import com.springboottest.restfulapi.model.Response;
import com.springboottest.restfulapi.model.SearchContactRequest;
import com.springboottest.restfulapi.model.UpdateContactRequest;
import com.springboottest.restfulapi.service.ContactService;
import org.springframework.data.domain.Page;

@RestController
public class ContactController {

  @Autowired
  private ContactService contactService;
  
  @PostMapping(path = "/api/contacts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<ContactResponse> create(User user, @RequestBody CreateContactRequest request) {
    ContactResponse contactResponse = contactService.create(user, request);

    return Response.<ContactResponse>builder().data(contactResponse).build();

  }

  @GetMapping(path = "/api/contacts/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<ContactResponse> get(User user, @PathVariable("contactId") String id) {
    ContactResponse contactResponse = contactService.get(user, id);

    return Response.<ContactResponse>builder().data(contactResponse).build();

  }

  @PutMapping(path = "/api/contacts/{contactId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<ContactResponse> update(User user, @RequestBody UpdateContactRequest request, @PathVariable("contactId") String id) {
    request.setId(id);
    ContactResponse contactResponse = contactService.update(user, request);

    return Response.<ContactResponse>builder().data(contactResponse).build();

  }

  @DeleteMapping(path = "/api/contacts/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<String> delete(User user, @PathVariable("contactId") String id) {
    contactService.delete(user, id);

    return Response.<String>builder().data("OK").build();

  }

  @GetMapping(path = "/api/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<List<ContactResponse>> search(User user, 
  @RequestParam(value = "name", required = false) String name, 
  @RequestParam(value = "email", required = false) String email, 
  @RequestParam(value = "phone", required = false) String phone, 
  @RequestParam(value = "page", required = false, defaultValue = "0") Integer page, 
  @RequestParam(value = "size", required = false, defaultValue = "0") Integer size
  ) {
    SearchContactRequest request = SearchContactRequest.builder().page(page).size(size).phone(phone).email(email).name(name).build();

    Page<ContactResponse> contactResponse = contactService.search(user, request);
    return Response.<List<ContactResponse>>builder().data(contactResponse.getContent()).paging(PagingResponse.builder().currentPage(contactResponse.getNumber()).totalPage(contactResponse.getTotalPages()).size(contactResponse.getSize()).build()).build();
  }
  
}

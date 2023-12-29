package com.springboottest.restfulapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.CreateAddressRequest;
import com.springboottest.restfulapi.model.CreateAddressResponse;
import com.springboottest.restfulapi.model.Response;
import com.springboottest.restfulapi.service.AddressService;
import org.springframework.http.MediaType;


@RestController
public class AddressController {
  @Autowired
  private AddressService addressService;

  @PostMapping(path = "/api/contacts/{contactId}/address")
  public Response<CreateAddressResponse> create(User user, @RequestBody CreateAddressRequest request, @PathVariable("contactId") String id) {
    request.setContactId(id);
    CreateAddressResponse createAddressResponse = addressService.create(user, request);
    return Response.<CreateAddressResponse>builder().data(createAddressResponse).build();
  }

  @GetMapping(path = "/api/contacts/{contactId}/address/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<CreateAddressResponse> get(User user, @PathVariable("contactId") String contactId, @PathVariable("addressId") String addressId) {
    CreateAddressResponse createAddressResponse = addressService.get(user, contactId, addressId);

    return Response.<CreateAddressResponse>builder().data(createAddressResponse).build();
  }
  
}

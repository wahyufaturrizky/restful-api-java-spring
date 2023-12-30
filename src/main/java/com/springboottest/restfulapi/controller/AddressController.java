package com.springboottest.restfulapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.CreateAddressRequest;
import com.springboottest.restfulapi.model.CreateAddressResponse;
import com.springboottest.restfulapi.model.Response;
import com.springboottest.restfulapi.model.UpdateAddressRequest;
import com.springboottest.restfulapi.service.AddressService;


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

  @PutMapping(path = "/api/contacts/{contactId}/address/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE))
  public Response<CreateAddressResponse> update(User user, @RequestBody UpdateAddressRequest request, @PathVariable("contactId") String id, @PathVariable("addressId") String addressId) {
    request.setContactId(id);
    request.setAddressId(addressId);
    CreateAddressResponse createAddressResponse = addressService.update(user, request);
    return Response.<CreateAddressResponse>builder().data(createAddressResponse).build();
  }

  @DeleteMapping(path = "/api/contacts/{contactId}/address/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<String> remove(User user, @PathVariable("contactId") String id, @PathVariable("addressId") String addressId) {
    
    addressService.remove(user, id, addressId);

    return Response.<String>builder().data("OK").build();
  }
  
}

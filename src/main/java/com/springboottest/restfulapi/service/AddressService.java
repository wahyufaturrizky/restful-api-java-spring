package com.springboottest.restfulapi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboottest.restfulapi.entity.Address;
import com.springboottest.restfulapi.entity.Contacts;
import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.CreateAddressRequest;
import com.springboottest.restfulapi.model.CreateAddressResponse;
import com.springboottest.restfulapi.repository.AddressRepository;
import com.springboottest.restfulapi.repository.ContactRepository;
import org.springframework.web.server.ResponseStatusException;


import jakarta.transaction.Transactional;

@Service
public class AddressService {

  @Autowired
  private ContactRepository contactRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private ValidationService validationService;

  @Transactional
  public CreateAddressResponse create(User user, CreateAddressRequest request) {
    validationService.validate(request);

    Contacts contacts = contactRepository.findFirstByUserAndId(user, request.getContactId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

    Address address = new Address();
    address.setId(UUID.randomUUID().toString());
    address.setContact(contacts);
    address.setStreet(request.getStreet());
    address.setCity(request.getCity());
    address.setProvince(request.getProvince());
    address.setPostalCode(request.getPostalCode());

    addressRepository.save(address);

    return toAddressResponse(address);
  }

  private CreateAddressResponse toAddressResponse(Address address) {
    return CreateAddressResponse.builder().id(address.getId())
    .street(address.getStreet())
    .city(address.getCity())
    .province(address.getProvince())
    .country(address.getCountry())
    .postalCode(address.getPostalCode()).build();
  }

  @Transactional(readOnly = true)
  public CreateAddressResponse get(User user, String contactId, String addressid) {
    Contacts contacts = contactRepository.findFirstByUserAndId(user, addressid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

    Address address = addressRepository.findFirstByContactAndId(contacts, contactId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));

    return toAddressResponse(address);
  }
  
}

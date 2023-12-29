package com.springboottest.restfulapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboottest.restfulapi.entity.Contacts;
import com.springboottest.restfulapi.entity.User;
import com.springboottest.restfulapi.model.ContactResponse;
import com.springboottest.restfulapi.model.CreateContactRequest;
import com.springboottest.restfulapi.model.SearchContactRequest;
import com.springboottest.restfulapi.model.UpdateContactRequest;
import com.springboottest.restfulapi.repository.ContactRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;

import jakarta.persistence.criteria.Predicate;
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

  @Transactional
  public void delete(User user, String id) {
    Contacts contacts = contactRepository.findFirstByUserAndId(user, id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

    contactRepository.delete(contacts);

  }

  @Transactional
  public Page<ContactResponse> search(User user, SearchContactRequest request) {
    Specification<Contacts> specification = (root, query, builder) -> {
      List<Predicate> predicates = new ArrayList<>();
      predicates.add(builder.equal(root.get("user"), user));
      if (Objects.nonNull(request.getName())) {
        predicates.add(builder.or(
          builder.like(root.get("firstName"), "%" + request.getName() + "%"),
          builder.like(root.get("lastName"), "%" + request.getName() + "%"),
        ))
      }

      if (Objects.nonNull(request.getEmail())) {
        predicates.add(builder.like(root.get("email"), "%" + request.getEmail() + "%"))
      }

      if (Objects.nonNull(request.getPhone())) {
        predicates.add(builder.like(root.get("phone"), "%" + request.getPhone() + "%"))
      }

      return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
    };
    
    Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

    Page<Contacts> contacts = contactRepository.findAll(specification, pageable);

    List<ContactResponse> contactResponse = contacts.getContent().stream().map(this::toContactResponse).toList();

    return new PageImpl<>(contactResponse, pageable, contacts.getTotalElements());

  }
  
}

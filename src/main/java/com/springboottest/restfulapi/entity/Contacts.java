package com.springboottest.restfulapi.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts", schema = "public")

public class Contacts {

  @Id
  private String id;

  @Column(insertable = false, updatable = false)
  private String username;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  private String phone;
  private String email;

  @ManyToOne
  @JoinColumn(name = "username", referencedColumnName = "username")
  private User user;

  @OneToMany(mappedBy = "contact")
  private List<Address> address;
  
}

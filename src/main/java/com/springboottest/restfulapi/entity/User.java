package com.springboottest.restfulapi.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "users", schema = "public")
public class User {

  @Id
  private String username;
  private String password;
  private String name;
  private String token;

  @Column(name = "token_expired_at")
  private Long tokenExpiredAt;

  @OneToMany(mappedBy = "user")
  private List<Contacts> contacts;
  
}

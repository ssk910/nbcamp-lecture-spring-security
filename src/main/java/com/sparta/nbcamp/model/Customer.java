package com.sparta.nbcamp.model;

import com.sparta.nbcamp.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "customer")
public class Customer {

  /**
   * PK.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 이메일.
   */
  @Column(unique = true)
  private String email;

  /**
   * 암호.
   */
  private String password;

  /**
   * 권한.
   */
  @Enumerated(value = EnumType.STRING)
  private Role role;

  public Customer(String email, String password, Role role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }
}


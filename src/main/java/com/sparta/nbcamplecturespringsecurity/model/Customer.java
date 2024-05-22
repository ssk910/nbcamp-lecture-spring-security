package com.sparta.nbcamplecturespringsecurity.model;

import com.sparta.nbcamplecturespringsecurity.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer extends User {

  /**
   * 권한.
   */
  private Role role;

  @Builder
  public Customer(Long id, String email, String password, Role role) {
    super(id, email, password, role);
  }

  @Override
  public String getUsername() {
    return super.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}


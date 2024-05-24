package com.sparta.nbcamp.model;

import com.sparta.nbcamp.dto.LoginRequest;
import com.sparta.nbcamp.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

  /**
   * 암호를 검증한다.
   *
   * @param request         {@link LoginRequest}
   * @param passwordEncoder {@link PasswordEncoder}
   * @throws IllegalArgumentException 암호가 일치하지 않을 때
   */
  public void validatePassword(LoginRequest request, PasswordEncoder passwordEncoder)
      throws IllegalArgumentException {
    boolean valid = passwordEncoder.matches(request.getPassword(), this.password);
    if (!valid) {
      throw new IllegalArgumentException("Wrong password.");
    }
  }
}


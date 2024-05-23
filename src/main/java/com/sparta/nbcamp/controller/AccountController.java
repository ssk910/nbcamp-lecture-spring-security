package com.sparta.nbcamp.controller;

import com.sparta.nbcamp.dto.CommonResponse;
import com.sparta.nbcamp.dto.LoginRequest;
import com.sparta.nbcamp.model.Customer;
import com.sparta.nbcamp.model.enums.Role;
import com.sparta.nbcamp.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 계정 관련 controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/accounts")
public class AccountController {

  private final CustomerService customerService;
  private final PasswordEncoder passwordEncoder;

  /**
   * 사용자의 계정을 등록.
   *
   * @param dto  {@link LoginRequest}
   * @param role 등록하고 싶은 권한. ({@link Role})
   * @return {@link ResponseEntity}<{@link CommonResponse}<{@link Void}>>
   */
  @PostMapping
  public ResponseEntity<CommonResponse<String>> postCustomerAccount(
      @Valid @RequestBody LoginRequest dto,
      @NotBlank @RequestParam String role) {
    Customer savedCustomer = customerService.save(new Customer(
        dto.getEmail(),
        passwordEncoder.encode(dto.getPassword()),
        Role.from(role)
    ));

    return ResponseEntity.ok()
        .body(new CommonResponse<>("계정 등록 완료.", savedCustomer.getEmail()));
  }

}

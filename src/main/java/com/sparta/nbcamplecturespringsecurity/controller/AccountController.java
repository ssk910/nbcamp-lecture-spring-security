package com.sparta.nbcamplecturespringsecurity.controller;

import com.sparta.nbcamplecturespringsecurity.dto.LoginDto;
import com.sparta.nbcamplecturespringsecurity.model.Customer;
import com.sparta.nbcamplecturespringsecurity.model.enums.Role;
import com.sparta.nbcamplecturespringsecurity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  public AccountController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping
  public ResponseEntity<HttpStatus> postCustomerAccount(@RequestBody LoginDto dto,
      @RequestParam Role role) {
    Customer build = Customer.builder()
        .email(dto.getEmail())
        .password(passwordEncoder.encode(dto.getPassword()))
        .role(role)
        .build();

    Customer savedCustomer = userService.save(build);

    return ResponseEntity.ok().build();
  }

}

package com.sparta.nbcamplecturespringsecurity.controller;


import com.sparta.nbcamplecturespringsecurity.dto.JWTAuthResponse;
import com.sparta.nbcamplecturespringsecurity.dto.LoginDto;
import com.sparta.nbcamplecturespringsecurity.service.AuthService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginDto loginDto) {
    JWTAuthResponse token = this.authService.login(loginDto);

    return ResponseEntity.ok(token);
  }

  @GetMapping("/admin")
  public ResponseEntity<String> admin(Authentication authentication) {
    return ResponseEntity.ok(this.createResponseBy(authentication));
  }

  @GetMapping("/user")
  public ResponseEntity<String> user(Authentication authentication) {
    return ResponseEntity.ok(this.createResponseBy(authentication));
  }

  @GetMapping("/customer")
  public ResponseEntity<String> customer(Authentication authentication) {
    return ResponseEntity.ok(this.createResponseBy(authentication));
  }

  private String createResponseBy(Authentication authentication) {
    String username = authentication.getName();
    List<? extends GrantedAuthority> authorities = authentication.getAuthorities().stream()
        .toList();

    return String.format("Hello! %s. Your role is \"%s\".", username, authorities);
  }
}

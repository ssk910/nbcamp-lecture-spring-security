package com.sparta.nbcamp.controller;


import com.sparta.nbcamp.dto.JwtAuthResponse;
import com.sparta.nbcamp.dto.LoginRequest;
import com.sparta.nbcamp.service.AuthService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  /**
   * 로그인.
   *
   * @param loginRequest 로그인 request body
   * @return {@link ResponseEntity}
   */
  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JwtAuthResponse> authenticate(
      @Valid @RequestBody LoginRequest loginRequest) {
    JwtAuthResponse token = this.authService.login(loginRequest);

    return ResponseEntity.ok(token);
  }

  /**
   * 모든 권한의 사용자가 접근 가능한 엔드 포인트.
   *
   * @param authentication 인증 객체
   * @return {@link ResponseEntity}
   */
  @GetMapping("/all")
  public ResponseEntity<String> user(Authentication authentication) {
    return ResponseEntity.ok(this.createResponseBy(authentication));
  }

  /**
   * CUSTOMER 권한의 사용자만 접근 가능한 엔드 포인트.
   *
   * @param authentication 인증 객체
   * @return {@link ResponseEntity}
   */
  @GetMapping("/customer")
  public ResponseEntity<String> customer(Authentication authentication) {
    return ResponseEntity.ok(this.createResponseBy(authentication));
  }

  /**
   * ADMIN 권한의 사용자만 접근 가능한 엔드 포인트.
   *
   * @param authentication 인증 객체
   * @return {@link ResponseEntity}
   */
  @GetMapping("/admin")
  public ResponseEntity<String> admin(Authentication authentication) {
    return ResponseEntity.ok(this.createResponseBy(authentication));
  }

  /**
   * 사용자 인증 정보가 담긴 response 메세지를 생성.
   *
   * @param authentication 인증 객체
   * @return 메세지
   */
  private String createResponseBy(Authentication authentication) {
    String username = authentication.getName();
    List<? extends GrantedAuthority> authorities = authentication.getAuthorities().stream()
        .toList();

    return String.format("Hello! %s. Your role is \"%s\".", username, authorities);
  }
}

package com.sparta.nbcamp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  /**
   * 현재 사용자의 인증 정보를 확인하기 위한 엔드 포인트. (토큰 필요)
   *
   * @param authentication 인증 객체
   * @return {@link java.security.Principal}
   */
  @GetMapping("/user/me")
  Object getMe(Authentication authentication) {
    return authentication.getPrincipal();
  }
}

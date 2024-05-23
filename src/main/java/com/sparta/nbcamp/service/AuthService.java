package com.sparta.nbcamp.service;


import com.sparta.nbcamp.config.jwt.JwtTokenProvider;
import com.sparta.nbcamp.dto.JwtAuthResponse;
import com.sparta.nbcamp.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 인증 서비스.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  /**
   * 로그인.
   * @param loginRequest {@link LoginRequest}
   * @return {@link JwtAuthResponse}
   */
  public JwtAuthResponse login(LoginRequest loginRequest) {
    // 사용자 인증 후 정보를 저장
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // 토큰 생성
    String token = this.jwtTokenProvider.generateToken(authentication);

    return new JwtAuthResponse(token, "Bearer",
        this.jwtTokenProvider.getJwtExpiryInMillis());
  }
}

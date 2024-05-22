package com.sparta.nbcamplecturespringsecurity.service;


import com.sparta.nbcamplecturespringsecurity.config.jwt.JwtTokenProvider;
import com.sparta.nbcamplecturespringsecurity.dto.JWTAuthResponse;
import com.sparta.nbcamplecturespringsecurity.dto.LoginDto;
import com.sparta.nbcamplecturespringsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;

  public JWTAuthResponse login(LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = this.jwtTokenProvider.generateToken(authentication);



    return new JWTAuthResponse(token, "Bearer",
        this.jwtTokenProvider.getJwtExpiryInMillis());
  }

  public JWTAuthResponse logout(LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = this.jwtTokenProvider.generateToken(authentication);

    return new JWTAuthResponse(token, "Bearer",
        this.jwtTokenProvider.getJwtExpiryInMillis());
  }
}

package com.sparta.nbcamp.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * JWT 정보가 담긴 response DTO.
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class JwtAuthResponse {

  /**
   * access token
   */
  private String accessToken;

  /**
   * 토큰 종류.
   */
  private String tokenType;

  /**
   * 토큰 만료 시간.
   */
  private Long expiresIn;
}

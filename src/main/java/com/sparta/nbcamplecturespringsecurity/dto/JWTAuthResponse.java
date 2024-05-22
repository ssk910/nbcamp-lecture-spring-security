package com.sparta.nbcamplecturespringsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class JWTAuthResponse {

    private String accessToken;
    private String tokenType;
    private Long expiresIn;
}

package com.sparta.nbcamp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그인 request의 DTO.
 */
@AllArgsConstructor
@Getter
public class LoginRequest {

    /**
     * 이메일 (username으로 식별).
     */
    @NotBlank(message = "An email should be not empty.")
    @Email(message = "Email format required.")
    private String email;

    /**
     * 암호.
     */
    @NotBlank(message = "A password should be not empty.")
    private String password;
}

package com.sparta.nbcamplecturespringsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginDto {

    /**
     * 이메일 (username으로 식별).
     */
    private String email;

    /**
     * 암호.
     */
    private String password;
}

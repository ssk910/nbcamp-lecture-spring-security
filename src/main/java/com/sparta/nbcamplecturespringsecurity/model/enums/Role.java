package com.sparta.nbcamplecturespringsecurity.model.enums;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {

    USER,
    ADMIN,
    CUSTOMER;


    public List<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }
}

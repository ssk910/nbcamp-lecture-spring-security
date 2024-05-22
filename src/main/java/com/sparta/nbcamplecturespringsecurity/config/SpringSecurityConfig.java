package com.sparta.nbcamplecturespringsecurity.config;


import com.sparta.nbcamplecturespringsecurity.config.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  private final AuthenticationProvider authenticationProvider;

  private static final String[] WHITE_LIST_URL = {"/auth/login", "/accounts"};

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth ->
            auth.requestMatchers(WHITE_LIST_URL).permitAll()
                .requestMatchers("/auth/admin").hasAnyRole("ADMIN")
                .requestMatchers("/auth/user").hasAnyRole("USER")
                .requestMatchers("/auth/customer").hasAnyRole("CUSTOMER")
                .anyRequest().authenticated()
        )
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}

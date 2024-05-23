package com.sparta.nbcamp.config.security;


import com.sparta.nbcamp.config.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security 설정.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  /**
   * JWT Filter.
   */
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  /**
   * AuthenticationProvider.
   */
  private final AuthenticationProvider authenticationProvider;

  /**
   * AuthenticationEntryPoint.
   */
  private final AuthenticationEntryPoint authEntryPoint;

  /**
   * 화이트 리스트.
   */
  private static final String[] WHITE_LIST_URL = {"/auth/login", "/accounts"};

  /**
   * security 필터.
   *
   * @param http {@link HttpSecurity}
   * @return {@link SecurityFilterChain} 필터 체인
   */
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth ->
            auth.requestMatchers(WHITE_LIST_URL).permitAll()
                .requestMatchers("/auth/admin").hasRole("ADMIN")
                .requestMatchers("/auth/customer", "/auth/all").hasAnyRole("ADMIN", "CUSTOMER")
                .anyRequest().authenticated()
        )
        .exceptionHandling(handler ->
            handler.authenticationEntryPoint(authEntryPoint)
        )
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


    return http.build();
  }

  /**
   * h2-console 접속은 Spring Security를 거치지 않도록 설정.
   *
   * @return {@link WebSecurityCustomizer}
   * @see <a
   * href="https://dukcode.github.io/spring/h2-console-with-spring-security/">spring-security에서-h2-console-사용하기</a>
   */
  @Bean
  @ConditionalOnProperty(name = "spring.h2.console.enabled", havingValue = "true")
  public WebSecurityCustomizer configureH2ConsoleEnable() {
    return web -> web.ignoring().requestMatchers(PathRequest.toH2Console());
  }
}

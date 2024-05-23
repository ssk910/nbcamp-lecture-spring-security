package com.sparta.nbcamp.config.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * <p>인증 실패에 대한 응답을 구성.</p>
 * {@link com.sparta.nbcamp.exception.GlobalExceptionHandler}에 정의.
 *
 * @see <a href="https://www.baeldung.com/spring-security-exceptionhandler">Handle Spring Security Exceptions</a>
 */
@Component
public class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Autowired
  @Qualifier("handlerExceptionResolver")
  private HandlerExceptionResolver resolver;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) {
    resolver.resolveException(request, response, null, authException);
  }
}
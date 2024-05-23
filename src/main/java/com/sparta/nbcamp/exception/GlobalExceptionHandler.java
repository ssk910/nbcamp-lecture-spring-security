package com.sparta.nbcamp.exception;

import com.sparta.nbcamp.dto.CommonResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

/**
 * Controller에서 response를 내보내기 전 각 Exception에 대해 처리.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * Validation 예외 처리.
   *
   * @param e HandlerMethodValidationException 인스턴스
   * @return {@code ResponseEntity<CommonResponse<String>>}
   */
  @ExceptionHandler(HandlerMethodValidationException.class)
  protected ResponseEntity<CommonResponse<String>> handleMethodValidationExceptions(
      HandlerMethodValidationException e) {
    String message = e.getAllValidationResults().get(0).getResolvableErrors().get(0)
        .getDefaultMessage();

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new CommonResponse<>(message, null));
  }

  /**
   * Validation 예외 처리.
   *
   * @param e MethodArgumentNotValidException 인스턴스
   * @return {@code ResponseEntity<CommonResponse<String>>}
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CommonResponse<String>> handleValidationExceptions(
      MethodArgumentNotValidException e) {
    String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new CommonResponse<>(message, null));
  }

  /**
   * Security와 관련된 AuthenticationException 예외 처리.
   *
   * @param e AuthenticationException 인스턴스
   * @return {@code ResponseEntity<CommonResponse<Void>>}
   */
  @ExceptionHandler(AuthenticationException.class)
  protected ResponseEntity<CommonResponse<Void>> handleAuthException(AuthenticationException e) {
    HttpStatus statusCode = e instanceof BadCredentialsException
        ? HttpStatus.FORBIDDEN
        : HttpStatus.UNAUTHORIZED;

    return ResponseEntity
        .status(statusCode)
        .body(new CommonResponse<>(e.getMessage(), null));
  }

  /**
   * entity 관련 예외 처리.
   *
   * @param e 던져지는 예외 인스턴스
   * @return {@code ResponseEntity<CommonResponse<Void>>}
   */
  @ExceptionHandler({EntityNotFoundException.class, DuplicateKeyException.class,
      IllegalArgumentException.class})
  protected ResponseEntity<CommonResponse<Void>> handleEntityException(Exception e) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new CommonResponse<>(e.getMessage(), null));
  }

  /**
   * 그외의 예외 처리.
   *
   * @param e 예외 인스턴스
   * @return {@code ResponseEntity<CommonResponse<Void>>}
   */
  @ExceptionHandler(Exception.class)
  protected ResponseEntity<CommonResponse<Void>> handleOtherExceptions(Exception e) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new CommonResponse<>(e.getMessage(), null));
  }
}
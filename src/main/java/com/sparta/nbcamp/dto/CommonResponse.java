package com.sparta.nbcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Response에 담을 공통 객체.
 *
 * @param <T> 데이터
 */
@Getter
@AllArgsConstructor
public class CommonResponse<T> {

  /**
   * 메세지.
   */
  private String message;

  /**
   * 데이터.
   */
  private T data;
}
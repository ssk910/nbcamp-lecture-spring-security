package com.sparta.nbcamp.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * 유저의 권한 정의.
 */
@Getter
@AllArgsConstructor
public enum Role {

  /**
   * 관리자 권한.
   */
  ADMIN("ADMIN"),

  /**
   * 고객 권한.
   */
  CUSTOMER("CUSTOMER"),
  ;

  /**
   * 권한 이름
   */
  private final String name;

  /**
   * 입력받은 이름에 해당하는 권한을 찾아 리턴.
   *
   * @param name 권한 이름
   * @return 해당하는 {@link Role}
   * @throws IllegalArgumentException 권한 이름이 잘못되었을 경우
   * @apiNote 대소문자 구분하지 않음
   */
  @JsonCreator
  public static Role from(String name) throws IllegalArgumentException {
    for (Role role : Role.values()) {
      if (name.toUpperCase().equals(role.getName())) {
        return role;
      }
    }

    throw new IllegalArgumentException("Invalid role name: " + name);
  }

  /**
   * {@link org.springframework.security.core.userdetails.UserDetails}에 담길 권한을 리턴.
   *
   * @return 권한 리스트. {@link List<SimpleGrantedAuthority>}
   */
  public List<SimpleGrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
  }
}

package com.sparta.nbcamp.config.auth;

import com.sparta.nbcamp.model.Customer;
import com.sparta.nbcamp.model.enums.Role;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * {@link UserDetails}의 구현체 클래스.
 */
@Getter
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

  /**
   * Customer entity.
   */
  private final Customer customer;

  /**
   * 계정의 권한 리스트를 리턴.
   *
   * @return {@code Collection<? extends GrantedAuthority>}
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Role role = this.customer.getRole();
    return new ArrayList<>(role.getAuthorities());
  }

  /**
   * 사용자의 자격 증명 반환.
   *
   * @return 암호
   */
  @Override
  public String getPassword() {
    return this.customer.getPassword();
  }

  /**
   * 사용자의 자격 증명 반환.
   *
   * @return 사용자 이름
   */
  @Override
  public String getUsername() {
    return this.customer.getEmail();
  }

  /**
   * 께정 만료.
   *
   * @return 사용 여부
   * @apiNote 사용할 경우 true를 리턴하도록 재정의.
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * 계정 잠금.
   *
   * @return 사용 여부
   * @apiNote 사용할 경우 true를 리턴하도록 재정의.
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * 자격 증명 만료.
   *
   * @return 사용 여부
   * @apiNote 사용할 경우 true를 리턴하도록 재정의.
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * 계정 비활성화.
   *
   * @return 사용 여부
   * @apiNote 사용할 경우 true를 리턴하도록 재정의.
   */
  @Override
  public boolean isEnabled() {
    return true;
  }
}

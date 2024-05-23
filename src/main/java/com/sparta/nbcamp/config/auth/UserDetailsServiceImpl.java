package com.sparta.nbcamp.config.auth;

import com.sparta.nbcamp.model.Customer;
import com.sparta.nbcamp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * {@link UserDetailsService}의 구현체 클래스.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  /**
   * Customer entity의 repository.
   */
  private final CustomerRepository customerRepository;

  /**
   * 입력받은 이메일에 해당하는 사용자 정보를 찾아 리턴.
   *
   * @param username username
   * @return 해당하는 유저의 {@link UserDetailsImpl} 객체
   * @throws UsernameNotFoundException 이메일에 해당하는 유저를 찾지 못한 경우
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Customer customer = this.customerRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return new UserDetailsImpl(customer);
  }
}

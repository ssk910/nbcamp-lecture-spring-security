package com.sparta.nbcamp.service;


import com.sparta.nbcamp.model.Customer;
import com.sparta.nbcamp.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository repository;

  /**
   * 사용자 저장.
   *
   * @param customer {@link Customer}
   * @return 저장된 {@link Customer}
   * @throws DuplicateKeyException 입력받은 이메일에 대한 사용자가 이미 있을 경우
   */
  public Customer save(Customer customer) throws DuplicateKeyException {
    boolean duplicatedEmail = this.repository.findByEmail(customer.getEmail()).isPresent();
    if (duplicatedEmail) {
      throw new DuplicateKeyException(
          String.format("Customer exists already with email: %s", customer.getEmail()));
    }

    return repository.save(customer);
  }

  /**
   * 입력받은 id에 대한 customer를 조회하여 리턴.
   *
   * @param id id
   * @return {@link Customer}
   * @throws EntityNotFoundException 해당하는 데이터가 없을 경우
   */
  public Customer findById(Long id) throws EntityNotFoundException {
    return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(
        String.format("Customer with that id %d does not exists", id)));
  }

  /**
   * 입력받은 email에 대한 customer를 조회하여 리턴.
   *
   * @param email 이메일
   * @return {@link Customer}
   * @throws EntityNotFoundException 해당하는 데이터가 없을 경우
   */
  public Customer findByEmail(String email) throws EntityNotFoundException {
    return repository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Customer with that email %s does not exists", email)));
  }
}

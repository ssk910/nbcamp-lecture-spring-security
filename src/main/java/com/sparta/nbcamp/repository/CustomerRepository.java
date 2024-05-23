package com.sparta.nbcamp.repository;

import com.sparta.nbcamp.model.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Customer 엔티티의 repository.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Optional<Customer> findById(Long id);

  Optional<Customer> findByEmail(String email);
}

package com.sparta.nbcamplecturespringsecurity.repository;

import com.sparta.nbcamplecturespringsecurity.model.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Customer 엔티티의 repository.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Optional<Customer> findByEmail(String email);
}

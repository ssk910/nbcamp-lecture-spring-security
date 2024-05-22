package com.sparta.nbcamplecturespringsecurity.service;


import com.sparta.nbcamplecturespringsecurity.model.Customer;
import com.sparta.nbcamplecturespringsecurity.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository repository;

  public CustomerService(CustomerRepository repository) {
    this.repository = repository;
  }

  public Customer save(Customer customer) {
    return repository.save(customer);
  }

  public Customer findById(Long id) {
    return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(
        String.format("Customer with that id %d does not exists", id)));
  }

  public Customer findByEmail(String email) {
    return repository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Customer with that email %s does not exists", email)));
  }

  public Optional<Customer> findByEmailOptional(String email) {
    return repository.findByEmail(email);
  }
}

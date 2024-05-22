package com.sparta.nbcamplecturespringsecurity.service;


import com.sparta.nbcamplecturespringsecurity.model.User;
import com.sparta.nbcamplecturespringsecurity.repository.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public <S extends User> S save(S user) {
    return repository.save(user);
  }

  public User findById(Long id) {
    return repository.findById(id).orElseThrow(
        () -> new RuntimeException(String.format("User with that id %d does not found", id)));
  }

  public Optional<? extends User> findByEmail(String email) {
    return repository.findByEmail(email);
  }
}

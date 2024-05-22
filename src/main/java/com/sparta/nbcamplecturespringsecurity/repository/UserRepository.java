package com.sparta.nbcamplecturespringsecurity.repository;

import com.sparta.nbcamplecturespringsecurity.model.User;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User 엔티티의 repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findById(@NonNull Long id);

  Optional<User> findByEmail(String email);
}

package com.sch.groomthon.hsx0306.repository;

import com.sch.groomthon.hsx0306.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
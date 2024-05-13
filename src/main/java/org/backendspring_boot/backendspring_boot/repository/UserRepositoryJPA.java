package org.backendspring_boot.backendspring_boot.repository;

import org.backendspring_boot.backendspring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJPA extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}

package org.backendspring_boot.backendspring_boot.repository;

import org.backendspring_boot.backendspring_boot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositoryJPA extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}

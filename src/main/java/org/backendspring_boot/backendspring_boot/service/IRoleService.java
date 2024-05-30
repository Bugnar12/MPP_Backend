package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(String name);
}

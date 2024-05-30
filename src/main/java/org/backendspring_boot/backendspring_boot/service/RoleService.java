package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.backendspring_boot.backendspring_boot.repository.RoleRepositoryJPA;

import java.util.Optional;

@Service
public class RoleService implements IRoleService{
    private final RoleRepositoryJPA roleRepositoryJPA;

    @Autowired
    public RoleService(RoleRepositoryJPA roleRepositoryJPA) {
        this.roleRepositoryJPA = roleRepositoryJPA;
    }

    @Override
    public Optional<Role> findByName(String name)
    {
        return roleRepositoryJPA.findByName(name);
    }
}

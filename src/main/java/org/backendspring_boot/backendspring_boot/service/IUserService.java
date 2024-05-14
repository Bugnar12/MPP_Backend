package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.User;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;

import java.security.NoSuchAlgorithmException;

public interface IUserService {
    User getUserById(Long id) throws RepositoryException;
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    void addUser(User user) throws RepositoryException;
    void login(User user) throws RepositoryException;
}

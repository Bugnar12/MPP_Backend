package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.User;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.backendspring_boot.backendspring_boot.utils.LoginRequest;

import java.security.NoSuchAlgorithmException;

public interface IUserService {
    User getUserById(Long id) throws RepositoryException;
    User getUserByUsername(String username);
    User getUserByEmail(String email);
<<<<<<< HEAD
    void addUser(User user) throws RepositoryException;
    User login(LoginRequest loginRequest) throws RepositoryException;
=======
    void addUser(User user) throws NoSuchAlgorithmException;
>>>>>>> parent of 023a057 (refactoring authentication + jwt implementation)
}

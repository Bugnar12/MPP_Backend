package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.User;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.backendspring_boot.backendspring_boot.repository.UserRepositoryJPA;
import org.backendspring_boot.backendspring_boot.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.channels.ReadPendingException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@Service
public class UserServiceImpl implements IUserService{

    private final UserRepositoryJPA userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepositoryJPA userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User getUserById(Long id) throws RepositoryException {
        return this.userRepository.findById(id).orElseThrow(() -> new RepositoryException("User not found!"));
    }

    ///TODO : throw exception when not found
    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    ///TODO : throw exception when not found
    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public void addUser(User user) throws RepositoryException{
        try {
            if (userRepository.findByUsername(user.getUsername()) != null) {
                throw new RepositoryException("Username already exists");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        catch(DataIntegrityViolationException e) {
            throw new RepositoryException("Username already exists!");
        }
    }

    public void login(User user) throws RepositoryException {
        User userfromDB = userRepository.findByUsername(user.getUsername());
        if(userfromDB == null)
        {
            throw new RepositoryException("User not found!");
        }
        if(!passwordEncoder.matches(user.getPassword(), userfromDB.getPassword()))
        {
            throw new RepositoryException("Invalid password!");
        }
    }
}

package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.User;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.backendspring_boot.backendspring_boot.repository.UserRepositoryJPA;
import org.backendspring_boot.backendspring_boot.utils.LoginRequest;
import org.backendspring_boot.backendspring_boot.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements IUserService{

    private final UserRepositoryJPA userRepository;

    @Autowired
    public UserServiceImpl(UserRepositoryJPA userRepository)
    {
        this.userRepository = userRepository;
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
    public void addUser(User user) throws NoSuchAlgorithmException {
        try {
            String hashedPassword = PasswordUtils.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
            userRepository.save(user);
        }
        catch(NoSuchAlgorithmException e)
        {

<<<<<<< HEAD
    @Override
    public User login(LoginRequest loginRequest) throws RepositoryException {
        User userfromDB = userRepository.findByUsername(loginRequest.getUsername());
        if(userfromDB == null || userfromDB.getId() == null)
        {
            throw new RepositoryException("User not found!");
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(), userfromDB.getPassword()))
        {
            throw new RepositoryException("Invalid password!");
=======
            e.printStackTrace();
>>>>>>> parent of 023a057 (refactoring authentication + jwt implementation)
        }
        return userfromDB;
    }
}

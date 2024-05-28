/*
package org.example.service;

import org.backendspring_boot.backendspring_boot.entity.User;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.backendspring_boot.backendspring_boot.repository.UserRepositoryJPA;
import org.backendspring_boot.backendspring_boot.service.UserServiceImpl;
import org.backendspring_boot.backendspring_boot.utils.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepositoryJPA userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUserSuccess() throws RepositoryException {
        User user = new User("andrei@test.com", "password", "andrei");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        userService.addUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testAddUserFailure() {
        User user = new User("andrei@test.com", "password", "andrei");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        assertThrows(RepositoryException.class, () -> userService.addUser(user));
    }


    ///test is broken, due to be fixed
*/
/*    @Test
    public void testLoginSuccess() throws RepositoryException {
        User user = new User("andrei@test.com", "password", "andrei");
        LoginRequest loginRequest = new LoginRequest("andrei", "password");
        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(user);
        when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(true);

        User result = userService.login(loginRequest);

        assertEquals(user, result);
    }*//*


    @Test
    public void testLoginFailureUserNotFound() {
        LoginRequest loginRequest = new LoginRequest("andrei", "password");
        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(null);

        assertThrows(RepositoryException.class, () -> userService.login(loginRequest));
    }

    @Test
    public void testLoginFailureInvalidPassword() {
        User user = new User("andrei@email.com", "password", "andrei");
        LoginRequest loginRequest = new LoginRequest("username", "wrongPassword");
        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(user);
        when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(RepositoryException.class, () -> userService.login(loginRequest));
    }
}*/

package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.User;
import org.backendspring_boot.backendspring_boot.repository.UserRepositoryJPA;
import org.backendspring_boot.backendspring_boot.utils.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepositoryJPA userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

/*    @Test
    public void testAddUser() throws Exception {
        User user = new User("andrei@test.com", "password", "andrei");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType("application/json")
                        .content("{\"email\":\"andrei@test.com\",\"password\":\"password\",\"username\":\"andrei\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }*/

/*    @Test
    public void testLogin() throws Exception {
        User user = new User("andrei@test.com", "password", "andrei");
        LoginRequest loginRequest = new LoginRequest("andrei", "password");
        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(user);
        when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .contentType("application/json")
                        .content("{\"username\":\"andrei\",\"password\":\"password\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }*/
}
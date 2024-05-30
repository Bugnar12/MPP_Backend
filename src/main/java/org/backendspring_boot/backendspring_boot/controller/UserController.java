package org.backendspring_boot.backendspring_boot.controller;


import jakarta.validation.Valid;
import org.backendspring_boot.backendspring_boot.entity.Role;
import org.backendspring_boot.backendspring_boot.entity.User;
import org.backendspring_boot.backendspring_boot.service.IRoleService;
import org.backendspring_boot.backendspring_boot.service.IUserService;
import org.backendspring_boot.backendspring_boot.service.JWTGeneratorService;
import org.backendspring_boot.backendspring_boot.utils.LoginRequest;
import org.backendspring_boot.backendspring_boot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private IUserService userService;
    private JWTGeneratorService jwtGeneratorService;
    private IRoleService roleService;

    @Autowired
    public UserController(IUserService userService, JWTGeneratorService jwtGeneratorService, IRoleService)
    {
        this.userService = userService;
        this.jwtGeneratorService = jwtGeneratorService;
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        }
        catch(Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid User user) {
        try{
            if(this.userService.getUserByEmail(user.getEmail()) != null)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists!");
            }
            userService.addUser(user);
            return ResponseEntity.ok("User registered successfully!");
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        User user;
        try{
            user = userService.login(loginRequest);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found or invalid password!");
        }

        String jsonWebToken = jwtGeneratorService.generateJWT(user);
        return ResponseEntity.ok().body(jsonWebToken);
    }
}

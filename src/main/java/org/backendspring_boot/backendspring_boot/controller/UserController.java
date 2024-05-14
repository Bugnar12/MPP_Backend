package org.backendspring_boot.backendspring_boot.controller;


import jakarta.validation.Valid;
import org.backendspring_boot.backendspring_boot.entity.User;
import org.backendspring_boot.backendspring_boot.service.IUserService;
import org.backendspring_boot.backendspring_boot.service.JWTGeneratorService;
import org.backendspring_boot.backendspring_boot.service.LoginRequest;
import org.backendspring_boot.backendspring_boot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {
    private IUserService userService;
    private final JWTGeneratorService jwtGeneratorService;

    @Autowired
    public UserController(IUserService userService, JWTGeneratorService jwtGeneratorService)
    {
        this.userService = userService;
        this.jwtGeneratorService = jwtGeneratorService;
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
            userService.addUser(user);
            return ResponseEntity.ok("User registered successfully!");
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists!");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid User user) {
        try{
            userService.login(user);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found!");
        }

        String jsonWebToken = jwtGeneratorService.generateJWT(user);
        return ResponseEntity.ok().body(jsonWebToken);
    }
}

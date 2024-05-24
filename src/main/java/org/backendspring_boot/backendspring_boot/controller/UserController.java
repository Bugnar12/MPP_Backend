package org.backendspring_boot.backendspring_boot.controller;


import org.backendspring_boot.backendspring_boot.entity.User;
import org.backendspring_boot.backendspring_boot.service.IUserService;
<<<<<<< HEAD
import org.backendspring_boot.backendspring_boot.service.JWTGeneratorService;
import org.backendspring_boot.backendspring_boot.utils.LoginRequest;
=======
import org.backendspring_boot.backendspring_boot.service.UserServiceImpl;
>>>>>>> parent of 023a057 (refactoring authentication + jwt implementation)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService)
    {
        this.userService = userService;
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

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

<<<<<<< HEAD

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
=======
>>>>>>> parent of 023a057 (refactoring authentication + jwt implementation)
}

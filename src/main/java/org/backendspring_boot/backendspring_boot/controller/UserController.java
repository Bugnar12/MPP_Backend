package org.backendspring_boot.backendspring_boot.controller;


import org.backendspring_boot.backendspring_boot.entity.User;
import org.backendspring_boot.backendspring_boot.service.IUserService;
import org.backendspring_boot.backendspring_boot.service.UserServiceImpl;
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

}

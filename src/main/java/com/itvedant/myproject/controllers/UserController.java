package com.itvedant.myproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.myproject.entities.User;
import com.itvedant.myproject.services.UserService;

import jakarta.validation.Valid;

//@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody @Valid User newUser){
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(this.service.addUser(newUser));
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.service.getAll());
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<?>getUserById(@PathVariable Integer id){
        User foundUser = this.service.getUser(id);
        if(foundUser == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("User with this id does not exist");
        else
            return ResponseEntity.ok(foundUser);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        User foundUser = this.service.getUser(id);
        if(foundUser == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("User with this id does not exist");
        else
            return ResponseEntity.ok(this.service.deleteUser(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable @Valid Integer id, @RequestBody User updatUser){
        User foundUser = this.service.getUser(id);
        if(foundUser == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("User with this id does not exist");
        else
            return ResponseEntity.ok(this.service.updateUser(id, updatUser));
    

    }
}
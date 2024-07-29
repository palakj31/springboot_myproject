package com.itvedant.myproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itvedant.myproject.entities.User;
import com.itvedant.myproject.repositories.UserRepositories;

@Service
public class UserService {
    @Autowired
    private UserRepositories userRepository;
    
 
    public User addUser(User newUser){
        return this.userRepository.save(newUser);
    }

    public Iterable<User> getAll(){
        return this.userRepository.findAll();
    }

    public User getUser(Integer id){
        return this.userRepository.findById(id).orElse(null);
    }

    public String deleteUser(Integer id){
        this.userRepository.deleteById(id);
        return "User Deleted";
    }
    public User updateUser(Integer id, User updateUser){
        updateUser.setId(id);
        return this.userRepository.save(updateUser);
    }
}

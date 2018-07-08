package com.springboot.chitter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;


    //GET	/users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }


    //GET	/users/{id}
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }


    //POST	/users
    //input - details of user
    //output - CREATED & Return the created URI
    //test - use https://www.getpostman.com/ or curl
    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        User savedUser = service.save(user);
    }

}

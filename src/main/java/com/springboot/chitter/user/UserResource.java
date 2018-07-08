package com.springboot.chitter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        User user = service.findOne(id);

        if(user==null) {
            throw new UserNotFoundException("id-"+ id);
        }
        return user;
    }


    //POST	/users
    //CREATED 201 Location → http://localhost:3000/users/4
    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}

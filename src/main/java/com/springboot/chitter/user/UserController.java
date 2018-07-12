package com.springboot.chitter.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;



    @CrossOrigin
    @GetMapping(path="/users", produces="application/json")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }


    @CrossOrigin
    @GetMapping(path="/users/{id}", produces="application/json")
    public Resource<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id-" + id);

        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }


    @CrossOrigin
    @DeleteMapping(path="/users/{id}", consumes="application/json", produces="application/json")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }


    @CrossOrigin
    @PostMapping(path="/users", consumes="application/json", produces="application/json")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    @CrossOrigin
    @GetMapping(path="/users/{id}/posts", produces="application/json")
    public List<Post> retrieveAllPosts(@PathVariable int id){

        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        return userOptional.get().getPosts();
    }


    @CrossOrigin
    @PostMapping(path="/users/{id}/posts", consumes="application/json", produces="application/json")
    public ResponseEntity createPost(@PathVariable int id, @RequestBody Post post) {

        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    @CrossOrigin
    @GetMapping(path="/users/{user_id}/posts/{post_id}", produces="application/json")
    public Resource<Post> retrievePost(@PathVariable int user_id, @PathVariable int post_id) {

        Optional<User> userOptional = userRepository.findById(user_id);
        Optional<Post> postOptional = postRepository.findById(post_id);

        if (!postOptional.isPresent() || !userOptional.isPresent())
            throw new PostNotFoundException("id-" + post_id);
        if (userOptional.get().getId() != postOptional.get().getUserId())
            throw new PostNotFoundException("id-" + post_id);


        Resource<Post> resource = new Resource<Post>(postOptional.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllPosts(user_id));
        resource.add(linkTo.withRel("all-posts"));

        return resource;
    }

}

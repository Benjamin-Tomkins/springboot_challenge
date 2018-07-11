package com.springboot.chitter.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


// @Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message="Email should have at least 2 characters")
    private String email;


    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<Comment> comments;


    protected User() {}

    public User(Integer id, String email) {
        super();
        this.id = id;
        this.email = email;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, email=%s]", id, email);
    }

}
package com.springboot.chitter.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    private String post_text;

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public Integer getUserId() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return String.format( "Post [id=%s, post_text=%s]", id, post_text);
    }
}

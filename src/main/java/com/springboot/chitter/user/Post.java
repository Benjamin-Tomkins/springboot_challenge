package com.springboot.chitter.user;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    private String post_text;


    private Date created;
//    private Date updated;


    @ManyToOne(fetch=FetchType.LAZY)
    private User user;


    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

//    @PreUpdate
//    protected void onUpdate() {
//        updated = new Date();
//    }


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

    public Date getCreated() {
        return created;
    }


    @Override
    public String toString() {
        return String.format( "Post [id=%s, post_text=%s, created=%s]", id, post_text, created);
    }
}

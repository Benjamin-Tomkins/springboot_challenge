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
    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;


    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Integer getUserId() {
        return user.getId();
    }

    public void setUser(User user) {

        this.user = user;
    }


    @Override
    public String toString() {
        return String.format( "Post [id=%s, description=%s]", id, description);
    }
}

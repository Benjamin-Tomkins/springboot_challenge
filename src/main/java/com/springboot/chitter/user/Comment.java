package com.springboot.chitter.user;

import javax.persistence.*;


@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Integer id;
    private String comment_text;

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    private Post post;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Integer getUserId() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPostId() { return post.getId(); }

    public void setPost(Post post) { this.post = post; }


    @Override
    public String toString() {
        return String.format( "Post [id=%s, comment_text=%s]", id, comment_text);
    }
}

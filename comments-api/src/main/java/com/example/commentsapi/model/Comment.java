package com.example.commentsapi.model;

import com.example.commentsapi.serializer.CommentSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "comments")
@JsonSerialize(using = CommentSerializer.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "username")
    private String username;

    @Column(name = "post_id")
    private long postId;

    public Comment() {}

//    public Comment(long id, String text) {
//        this.id = id;
//        this.text = text;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long post_id) {
        this.postId = post_id;
    }
}

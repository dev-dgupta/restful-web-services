package com.interviewMe.rest.webservices.restfulwebservices.databases;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Posts {

    @Id
    @GeneratedValue
    private Integer postId;

    @NotNull(message = "post content cannot be null")
    private String postContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Users users;

    protected Posts() {
    }

    public Posts(Integer postId, String postContent) {
        this.postId = postId;
        this.postContent = postContent;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "postId=" + postId +
                ", postContent='" + postContent + '\'' +
                '}';
    }
}

package com.interviewMe.rest.webservices.restfulwebservices.user;

import javax.validation.constraints.NotNull;

public class UserPost {

    private Integer postId;
    @NotNull(message = "post content cannot be null")
    private String postContent;

    protected UserPost() {
    }

    public UserPost(Integer postId, String postContent) {
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

    @Override
    public String toString() {
        return "UserPost{" +
                "postId=" + postId +
                ", postContent='" + postContent + '\'' +
                '}';
    }
}

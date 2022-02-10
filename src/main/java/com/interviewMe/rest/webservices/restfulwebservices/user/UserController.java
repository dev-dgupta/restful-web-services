package com.interviewMe.rest.webservices.restfulwebservices.user;

import com.interviewMe.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDAOService userService;
    @Autowired
    private UserPostsDAOService userPostsService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = userService.findUser(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }
        EntityModel<User> entityModel= EntityModel.of(user);

        //link to all users
        WebMvcLinkBuilder linkToAllUsers=linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkToAllUsers.withRel("all-users"));

        //link to retrieve all post for a user
        WebMvcLinkBuilder linkToAllPostsOfUser=linkTo(methodOn(this.getClass()).retrieveAllPostsForUser(id));
        entityModel.add(linkToAllPostsOfUser.withRel("all-posts"));

        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        User user = userService.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);

//        if (savedUser == null) {
//            throw new UserNotCreatedException("User name cannot be empty" + user.getName());
//        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
//        return user;
    }

    @GetMapping("/users/{id}/posts")
    public List<UserPost> retrieveAllPostsForUser(@PathVariable int id) {
        User user = userService.findUser(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }

        return userPostsService.findAllPostsOfUser(user);
    }

    @GetMapping("/users/{id}/posts/{post_id}")
    public UserPost retrievePostForUser(@PathVariable int id, @PathVariable int post_id) {
        User user = userService.findUser(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }

        return userPostsService.findPostForUser(user.getId(), post_id);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody UserPost userPost) {
        User user = userService.findUser(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }
//        if (userPost.getPostContent().isEmpty()) {
//            throw new PostNotEmptyException("Post content cannot be empty " + userPost.getPostContent());
//        }
        User user1 = userPostsService.saveNewPostForUser(user, userPost);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/users/" + user.getId() + "/posts/{post_id}")
                        .buildAndExpand(user1.getUserPosts().get(0).getPostId()).toUri();
        return ResponseEntity.created(location).build();
//        return user;
    }


}

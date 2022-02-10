package com.interviewMe.rest.webservices.restfulwebservices.databases;

import com.interviewMe.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserPostRepository userPostRepository;

    @GetMapping("/jpa/users")
    public List<Users> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<Users> retrieveUser(@PathVariable int id) {
        Optional<Users> jpaUser = userRepository.findById(id);
        if (!jpaUser.isPresent()) {
            throw new UserNotFoundException(String.format("JpaUser with id %s not found", id));
        }
        EntityModel<Users> entityModel = EntityModel.of(jpaUser.get());

        //link to all users
        WebMvcLinkBuilder linkToAllUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkToAllUsers.withRel("all-users"));

        return entityModel;

    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody Users users) {
        Users savedUsers = userRepository.save(users);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUsers.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Posts> retrieveAllPostsForUser(@PathVariable int id) {
        Optional<Users> jpaUser = userRepository.findById(id);
        if (!jpaUser.isPresent()) {
            throw new UserNotFoundException(String.format("JpaUser with id %s not found", id));
        }

        return jpaUser.get().getPosts();
    }

//    @GetMapping("/jpa/users/{id}/posts/{post_id}")
//    public JpaUserPost retrievePostForUser(@PathVariable int id, @PathVariable int post_id) {
//        Optional<JpaUser> jpaUserOptional = userRepository.findById(id);
//        if (!jpaUserOptional.isPresent()) {
//            throw new UserNotFoundException(String.format("JpaUser with id %s not found", id));
//        }
//
//        return userPostRepository.findById(post_id);
//    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Posts posts) {
        Optional<Users> jpaUserOptional = userRepository.findById(id);
        if (!jpaUserOptional.isPresent()) {
            throw new UserNotFoundException(String.format("JpaUser with id %s not found", id));
        }
        posts.setUsers(jpaUserOptional.get());
        Posts userPost = userPostRepository.save(posts);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/users/" + jpaUserOptional.get().getId() + "/posts/{post_id}")
                        .buildAndExpand(userPost.getPostId()).toUri();
        return ResponseEntity.created(location).build();
    }


}

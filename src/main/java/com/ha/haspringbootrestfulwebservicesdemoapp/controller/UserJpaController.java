package com.ha.haspringbootrestfulwebservicesdemoapp.controller;

import com.ha.haspringbootrestfulwebservicesdemoapp.domain.Post;
import com.ha.haspringbootrestfulwebservicesdemoapp.domain.User;
import com.ha.haspringbootrestfulwebservicesdemoapp.exception.UserNotFoundException;
import com.ha.haspringbootrestfulwebservicesdemoapp.repository.PostRepository;
import com.ha.haspringbootrestfulwebservicesdemoapp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserJpaController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // Get all users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }


    // Create a user
    @PostMapping("/jpa/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User saveUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // Get a specific user
    // HATEOAS implementation
    @GetMapping("/jpa/users/{id}")
    public EntityModel<Optional<User>> retrieveOneUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        // Entity model
        EntityModel<Optional<User>> entityModel = EntityModel.of(user);

        // WebMvcLinkBuilder
        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(webMvcLinkBuilder.withRel("all-users"));

        return entityModel;
    }

    // Delete a specific user
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    // Get all posts of a specific user
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        return user.get().getPosts();
    }

    // Create a post for a user
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}

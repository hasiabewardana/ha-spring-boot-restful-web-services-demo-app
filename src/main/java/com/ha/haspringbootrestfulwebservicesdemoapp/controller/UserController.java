package com.ha.haspringbootrestfulwebservicesdemoapp.controller;

import com.ha.haspringbootrestfulwebservicesdemoapp.dao.UserDaoService;
import com.ha.haspringbootrestfulwebservicesdemoapp.domain.User;
import com.ha.haspringbootrestfulwebservicesdemoapp.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    // Get all users
    // URI versioning
    @GetMapping("/v1/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    // Get all users names only
    // URI versioning
    @GetMapping("/v2/users")
    public List<String> retrieveAllUsersNames() {
        return userDaoService.findAll().stream().map(User::getName).toList();
    }

    // Create a user
    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User saveUser = userDaoService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // Get a specific user
    @GetMapping("/users/{id}")
    public User retrieveOneUser(@PathVariable int id) {
        User user = userDaoService.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id: " + id);
        }

        return user;
    }

    // Delete a specific user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userDaoService.deleteUser(id);
    }
}

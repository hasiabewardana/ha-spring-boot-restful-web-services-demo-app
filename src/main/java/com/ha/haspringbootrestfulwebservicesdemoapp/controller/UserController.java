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

@RestController
public class UserController {

    private final UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    // Get all users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    // Get all users
    // URI versioning
    @GetMapping("/v1/users")
    public List<User> retrieveAllUsersUriVersion1() {
        return userDaoService.findAll();
    }

    // Get all users
    // URI versioning
    @GetMapping("/v2/users")
    public List<User> retrieveAllUsersUriVersion2() {
        return userDaoService.findAll();
    }

    // Get all users
    // Request param versioning
    @GetMapping(value = "/users", params = "version=1")
    public List<User> retrieveAllUsersRequestParamVersion1() {
        return userDaoService.findAll();
    }

    // Get all users
    // Request param versioning
    @GetMapping(value = "/users", params = "version=2")
    public List<User> retrieveAllUsersRequestParamVersion2() {
        return userDaoService.findAll();
    }

    // Get all users
    // Request header versioning
    @GetMapping(value = "/users/header", headers = "X-API-VERSION=1")
    public List<User> retrieveAllUsersRequestHeaderVersion1() {
        return userDaoService.findAll();
    }

    // Get all users
    // Request header versioning
    @GetMapping(value = "/users/header", headers = "X-API-VERSION=2")
    public List<User> retrieveAllUsersRequestHeaderVersion2() {
        return userDaoService.findAll();
    }

    // Get all users
    // Accept header versioning
    @GetMapping(value = "/users/accept1", produces = "application/vnd.company.app-v1+json")
    public List<User> retrieveAllUsersAcceptHeaderVersion1() {
        return userDaoService.findAll();
    }

    // Get all users
    // Accept header versioning
    @GetMapping(value = "/users/accept2", produces = "application/vnd.company.app-v2+json")
    public List<User> retrieveAllUsersAcceptHeaderVersion2() {
        return userDaoService.findAll();
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

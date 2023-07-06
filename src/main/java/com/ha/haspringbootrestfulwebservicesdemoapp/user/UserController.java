package com.ha.haspringbootrestfulwebservicesdemoapp.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @PostMapping("/users")
    public void saveUser(@RequestBody User user) {
        userDaoService.saveUser(user);
    }

    @RequestMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @RequestMapping("/users/{id}")
    public User retrieveOneUser(@PathVariable int id) {
        return userDaoService.findOne(id);
    }
}

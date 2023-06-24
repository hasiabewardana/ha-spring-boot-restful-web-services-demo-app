package com.ha.haspringbootrestfulwebservicesdemoapp.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @RequestMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    @RequestMapping("/users/{id}")
    public User retrieveOneUser(@PathVariable int id){
        return userDaoService.findOne(id);
    }
}

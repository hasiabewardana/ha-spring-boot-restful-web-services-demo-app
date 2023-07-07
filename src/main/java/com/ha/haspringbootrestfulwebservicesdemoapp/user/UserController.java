package com.ha.haspringbootrestfulwebservicesdemoapp.user;

import com.ha.haspringbootrestfulwebservicesdemoapp.exception.UserNotFoundException;
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

    @RequestMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User saveUser = userDaoService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping("/users/{id}")
    public User retrieveOneUser(@PathVariable int id) {
        User user = userDaoService.findOne(id);

        if(user == null){
            throw new UserNotFoundException("id: " + id);
        }

        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userDaoService.deleteUser(id);
    }
}

package com.ha.haspringbootrestfulwebservicesdemoapp.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(2, "Steve", LocalDate.now().minusYears(35)));
        users.add(new User(3, "John", LocalDate.now().minusYears(40)));
    }

    public static List<User> findAll() {
        return users;
    }
}

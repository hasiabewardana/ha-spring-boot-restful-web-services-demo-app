package com.ha.haspringbootrestfulwebservicesdemoapp.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int count = 0;

    static {
        users.add(new User(++count, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++count, "Steve", LocalDate.now().minusYears(35)));
        users.add(new User(++count, "John", LocalDate.now().minusYears(40)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User saveUser(User user) {
        user.setId(++count);
        users.add(user);

        return user;
    }
}

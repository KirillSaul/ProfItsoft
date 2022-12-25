package ua.fourthAssignment.spring.repository;

import org.springframework.stereotype.Component;
import ua.fourthAssignment.spring.model.User;

import java.util.List;

@Component
public class UserRepository {
    private final List<User> users = List.of(new User("Oleg1", "user1", "1")
            , new User("Oleg2", "user2", "2")
            , new User("Oleg3", "user3", "3"));

    public List<User> findAllUsers() {
        return users;
    }
}

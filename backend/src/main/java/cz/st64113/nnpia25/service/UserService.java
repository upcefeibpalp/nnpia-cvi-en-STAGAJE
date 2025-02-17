package cz.st64113.nnpia25.service;

import cz.st64113.nnpia25.domain.User;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;

@Service
public class UserService {
    private static final HashMap<Long, User> users = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostConstruct
    public void init() {
        User u1 = new User(1L, "John Doe", "john@doe.com");
        User u2 = new User(2L, "Jane Doe", "jane@doe.com");
        users.put(u1.getId(), u1);
        users.put(u2.getId(), u2);
    }

    public String findUser(Long id) {
        User user = users.get(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        logger.debug("User found: {}", user.toString());
        return user.toString();
    }

    public Collection<User> findAllUsers() {
        logger.debug("Finding all users");
        return users.values();
    }
}

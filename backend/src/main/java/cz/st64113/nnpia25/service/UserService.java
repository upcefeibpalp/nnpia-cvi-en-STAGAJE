package cz.st64113.nnpia25.service;

import cz.st64113.nnpia25.domain.User;
import cz.st64113.nnpia25.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        //User u1 = new User(1L, "John Doe", "john@doe.com");
        //User u2 = new User(2L, "Jane Doe", "jane@doe.com");
        //users.put(u1.getId(), u1);
        //users.put(u2.getId(), u2);
    }

    public User findUserById(Long id) {
        Optional<User> res = userRepository.findById(id);
        if (res.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        User user = res.get();
        log.debug("User found: {}", user.toString());
        return user;
    }

    public Collection<User> findAllUsers() {
        log.debug("Finding all users");
        return userRepository.findAll();
    }

    public Collection<User> findUsersByEmail(String email) {
        log.debug("Finding all users with email: {}", email);
        return userRepository.findAllByEmail(email);
    }

    public User createUser(User user) {
        if (userRepository.existsByIdOrEmail(user.getId(), user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
        return userRepository.save(user);
    }
}

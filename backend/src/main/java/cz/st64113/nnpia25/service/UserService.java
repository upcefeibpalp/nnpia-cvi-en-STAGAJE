package cz.st64113.nnpia25.service;

import cz.st64113.nnpia25.domain.User;
import cz.st64113.nnpia25.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
        //left here for educational purposes
        //User u1 = new User(1L, "John Doe", "john@doe.com");
        //User u2 = new User(2L, "Jane Doe", "jane@doe.com");
        //users.put(u1.getId(), u1);
        //users.put(u2.getId(), u2);
    }

    private User addUser(User user) {
        log.debug("New user created: {}", user.toString());
        return userRepository.save(user);
    }

    private User modifyUser(User oldUser, User newUser) {
        oldUser.setPassword(newUser.getPassword());
        oldUser.setEmail(newUser.getEmail());
        log.debug("User updated: {}", oldUser.toString());
        return userRepository.save(oldUser);
    }

    private void checkIfEmailUsed(String email, Long id) {
        if (userRepository.existsByEmailAndNotId(email, id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used by another user");
        }
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
        Optional<User> res = userRepository.findByEmail(email);
        if (res.isEmpty()) {
            return Collections.emptyList();
        }

        User user = res.get();
        log.debug("User found: {}", user.toString());
        return List.of(user);
    }

    public User createUser(User user) {
        if (userRepository.existsByIdOrEmail(user.getId(), user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
        return addUser(user);
    }

    public User deleteUser(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
        log.debug("User deleted: {}", user.toString());
        return user;
    }

    public User updateUser(Long id, User user) {
        checkIfEmailUsed(user.getEmail(), user.getId());
        Optional<User> res = userRepository.findById(id);
        if (res.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return modifyUser(res.get(), user);
    }

    public Pair<User, Boolean> updateOrCreateUser(User user) {
        checkIfEmailUsed(user.getEmail(), user.getId());
        Optional<User> res = userRepository.findById(user.getId());
        if (res.isEmpty()) {
            return new Pair<>(addUser(user), true);
        }
        return new Pair<>(modifyUser(res.get(), user), false);
    }
}

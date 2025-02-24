package cz.st64113.nnpia25.controller;

import cz.st64113.nnpia25.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cz.st64113.nnpia25.service.UserService;

import java.util.Collection;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Collection<User> findAllUsers(@RequestParam(required = false) String email) { return (email == null || email.isBlank()) ? userService.findAllUsers() : userService.findUsersByEmail(email); }

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }
}
package cz.st64113.nnpia25.controller;

import cz.st64113.nnpia25.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import cz.st64113.nnpia25.service.UserService;

import java.util.Collection;

@RestController
public class UserController {
    private final UserService _userService;

    public UserController(UserService userService) {
        this._userService = userService;
    }

    @GetMapping("/user")
    public Collection<User> findAllUsers() { return _userService.findAllUsers(); }

    @GetMapping("/user/{id}")
    public String findUser(@PathVariable Long id) {
        return _userService.findUser(id);
    }
}
package cz.st64113.nnpia25.controller;

import cz.st64113.nnpia25.domain.User;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cz.st64113.nnpia25.service.UserService;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<User> findAllUsers(@RequestParam(required = false) String email) { return (email == null || email.isBlank()) ? userService.findAllUsers() : userService.findUsersByEmail(email); }

    @GetMapping("/{id}")
    public User findUser(@PathVariable Long id) { return userService.findUserById(id); }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User res = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable Long id) { return userService.deleteUser(id); }

    @PostMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        if (user.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id mismatch");
        }
        return userService.updateUser(id, user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateOrCreateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        if (user.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id mismatch");
        }

        Pair<User, Boolean> res = userService.updateOrCreateUser(user);
        return ResponseEntity.status((res.b) ? HttpStatus.CREATED : HttpStatus.OK).body(res.a);
    }
}
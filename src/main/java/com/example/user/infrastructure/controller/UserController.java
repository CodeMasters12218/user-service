package com.example.user.infrastructure.controller;

import com.example.user.domain.model.User;
import com.example.user.domain.ports.UserServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServicePort service;

    public UserController(UserServicePort service) {
        this.service = service;
    }

    @GetMapping
    public List<User> findAllUsers() {
        return service.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping("/{id}")
    public User getByIdUser(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user) {
        User updated = service.update(id, user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.user.infrastructure.controller;

import com.example.user.domain.model.User;
import com.example.user.domain.ports.UserServicePort;
import com.example.user.infrastructure.repository.UserEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServicePort service;

    public UserController(UserServicePort service) {
        this.service = service;
    }

    @Operation(
        summary = "Get all users",
        description = "Returns a list of all users registered in the system."
    )
    @GetMapping
    public List<User> findAllUsers() {
        return service.findAll();
    }

    @Operation(
        summary = "Create a new user",
        description = "Creates a new user with the provided details."
    )
    @PostMapping
    public User save(@RequestBody
                     @Parameter(description = "User payload to be created") User user) {
        return service.save(user);
    }

    @Operation(
        summary = "Get user by ID",
        description = "Retrieves a user by its unique identifier."
    )
    @GetMapping("/{id}")
    public User getByIdUser(@PathVariable
                            @Parameter(description = "Unique identifier of the user", example = "10") Integer id) {
        return service.findById(id);
    }

    @Operation(
        summary = "Update user",
        description = "Updates an existing user's information by ID."
    )
    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable
            @Parameter(description = "Unique identifier of the user to update", example = "10") Integer id,
            @RequestBody
            @Parameter(description = "Updated user payload") User user) {
        User updated = service.update(id, user);
        return ResponseEntity.ok(updated);
    }

    @Operation(
        summary = "Delete user",
        description = "Deletes a user by its unique identifier."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable
                                       @Parameter(description = "Unique identifier of the user to delete", example = "10") Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Find users by exact name",
        description = "Returns users whose name matches exactly the provided value."
    )
    @GetMapping("/nombre/{nombre}")
    public List<UserEntity> getByNombre(@PathVariable
                                        @Parameter(description = "Exact name to search", example = "Ismael") String nombre) {
        return service.findByName(nombre);
    }

    @Operation(
        summary = "Find users by name containing fragment",
        description = "Returns users whose name contains the given fragment (case-insensitive)."
    )
    @GetMapping("/contiene/{fragment}")
    public List<UserEntity> getByNombreContiene(@PathVariable
                                                @Parameter(description = "Name fragment to search", example = "ism") String fragment) {
        return service.findByNameContaining(fragment);
    }

    @Operation(
        summary = "Find users by name prefix",
        description = "Returns users whose name starts with the given prefix."
    )
    @GetMapping("/prefijo/{prefix}")
    public List<UserEntity> getByPrefijo(@PathVariable
                                         @Parameter(description = "Prefix to search", example = "Is") String prefix) {
        return service.buscarPorPrefijo(prefix);
    }
}